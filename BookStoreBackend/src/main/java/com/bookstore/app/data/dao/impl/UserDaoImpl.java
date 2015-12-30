package com.bookstore.app.data.dao.impl;

import com.bookstore.app.commons.bo.CityTO;
import com.bookstore.app.commons.bo.CountryTO;
import com.bookstore.app.commons.bo.UserProfileTO;
import com.bookstore.app.commons.bo.UserTO;
import com.bookstore.app.commons.exceptions.SavingObjectException;
import com.bookstore.app.commons.exceptions.UserAuthenticationException;
import com.bookstore.app.data.dao.IUserDao;
import com.bookstore.app.data.entites.City;
import com.bookstore.app.data.entites.Country;
import com.bookstore.app.data.entites.User;
import com.bookstore.app.data.entites.UserProfile;
import com.bookstore.app.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cdinu on 11/13/2015.
 */
@Component
@Qualifier("userDao")
public class UserDaoImpl implements IUserDao {

    private SessionFactory sessionFactory;

    /**
     * The logging object.
     */
    private static Logger logger = Logger.getLogger(UserDaoImpl.class);

    public SessionFactory getSessionFactory() {
        if(sessionFactory == null){
            sessionFactory = HibernateUtil.getSessionFactory();
        }
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public UserTO login(String userName, String password) throws UserAuthenticationException{

        Session session = getSessionFactory().openSession();

        boolean userFound = false;
        UserTO foundUserTO = null;

        try {
            session.beginTransaction();

            Criteria criteria = session.createCriteria(User.class);
            List<User> dbUsers = criteria.list();

            for (User dbU : dbUsers){

                if(dbU.getUserName().equals(userName) && dbU.getPassword().equals(password)){
                    userFound = true;
                    foundUserTO = dbU.asTO();
                    break;
                }

            }

        }catch (Exception e){
            //log the error
        }finally {
            session.close();
        }

        if(userFound){
          return foundUserTO;

        }else{
            String errorMessage = "Invalid account credentials."
                    + " Username or password is incorect";

            throw new UserAuthenticationException(errorMessage);
        }

    }

    public UserTO getUserByName(String userName) {
        return new UserTO( null,"Vasile", "pass", null);
    }

    public List<UserTO> getAllUsers() {

        Session session = getSessionFactory().openSession();

        List<UserTO> allUsers = new ArrayList<UserTO>();

        try {
            session.beginTransaction();

            Criteria criteria = session.createCriteria(User.class);
            List<User> dbUsers = criteria.list();

            for (User dbU : dbUsers){
               allUsers.add(dbU.asTO());
            }

        }catch (Exception e){
            //log the error
            System.out.println(e);
        }finally {
            session.close();
        }


        return  allUsers;
    }

    public UserTO saveUser(UserTO userTO) throws SavingObjectException {
        User user = new User();

        if(userTO.getId() != null){
             user.setId(userTO.getId());
        }
        user.setUserName(userTO.getUserName());
        user.setPassword(userTO.getPassword());

        UserProfileTO userProfileTO = userTO.getUserProfileTO();

        if(userProfileTO != null) {

            CountryTO countryTO = userProfileTO.getCountryTO();
            Country country = new Country(countryTO.getId(), countryTO.getName());

            CityTO cityTO = userProfileTO.getCityTO();
            City city = new City(cityTO.getId(),country, cityTO.getName());

            UserProfile userProfile = new UserProfile();
            userProfile.setAddress(userProfileTO.getAddress());
            userProfile.setCity(city);
            userProfile.setCountry(country);
            userProfile.setEmailAddress(userProfileTO.getEmailAddress());
            userProfile.setFirstName(userProfileTO.getFirstName());
            userProfile.setLastName(userProfileTO.getLastName());
            userProfile.setId(userProfileTO.getId());
            userProfile.setPhoneNumber(userProfileTO.getPhoneNumber());
            userProfile.setPin(userProfileTO.getPin());
            userProfile.setUser(user);

            user.setUserProfile(userProfile);
        }

        Session session = getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(user);

        } catch (Exception e) {
            session.getTransaction().rollback();
            logger.error(
                    "An exception occurred while saving a user to database."
                            + HibernateUtil.TRANSACTION_ROLLBACK, e);
            throw new SavingObjectException(e.getMessage(), e);

        } finally {
            session.getTransaction().commit();
            session.close();
            logger.info(HibernateUtil.TRANSACTION_COMMITED_SUCCESSFULLY + " "
                    + HibernateUtil.SESSION_STOP);
        }

        return user.asTO();
    }

    public UserTO getUserById(int userId) {
        return null;
    }
}
