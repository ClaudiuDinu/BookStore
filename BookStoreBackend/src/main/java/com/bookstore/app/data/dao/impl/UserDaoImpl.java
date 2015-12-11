package com.bookstore.app.data.dao.impl;

import com.bookstore.app.commons.bo.UserTO;
import com.bookstore.app.commons.exceptions.UserAuthenticationException;
import com.bookstore.app.data.dao.IUserDao;
import com.bookstore.app.data.entites.User;
import com.bookstore.app.utils.HibernateUtil;
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

    public SessionFactory getSessionFactory() {
        if(sessionFactory == null){
            sessionFactory = HibernateUtil.getSessionFactory();
        }
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public UserTO login(UserTO userTO) throws UserAuthenticationException{

        Session session = getSessionFactory().openSession();

        boolean userFound = false;
        UserTO foundUserTO = null;

        try {
            session.beginTransaction();

            Criteria criteria = session.createCriteria(User.class);
            List<User> dbUsers = criteria.list();

            for (User dbU : dbUsers){

                if(dbU.getUserName().equals(userTO.getUserName()) && dbU.getPassword().equals(userTO.getPassword())){
                    userFound = true;
                    foundUserTO = dbU.asTO();
                }
                break;
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
        return new UserTO( null,"Vasile", "pass");
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
        }finally {
            session.close();
        }


        return  allUsers;
    }
}
