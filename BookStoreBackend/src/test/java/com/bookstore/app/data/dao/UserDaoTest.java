package com.bookstore.app.data.dao;

import com.bookstore.app.commons.bo.CityTO;
import com.bookstore.app.commons.bo.CountryTO;
import com.bookstore.app.commons.bo.UserProfileTO;
import com.bookstore.app.commons.bo.UserTO;
import com.bookstore.app.data.dao.impl.UserDaoImpl;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by cdinu on 11/13/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/appServlet/servlet-context.xml")
public class UserDaoTest {

    private List<Class> configurationClassList;
    private SessionFactory sessionFactory;
    private Configuration config;

    @Resource
    @Qualifier("userDao")
    private UserDaoImpl userDao;


    @Test
    public void testLogin() {

        UserTO loggedUser = null;

       String userName = "user";
       String password = "user";


        try {
            loggedUser = userDao.login(userName, password);
        } catch (Exception e) {

        }

        assertNotNull(loggedUser);
    }

    @Test
    public void createUserTest() {
        UserTO savedUser = null;
        UserTO userTO = new UserTO(12L,"tst","tst",null);

        CountryTO countryTO = new CountryTO(1L, "ROMANIA");
        CityTO cityTO = new CityTO(1L, "Sibiu", countryTO);
        UserProfileTO profileTO = new UserProfileTO(1L, "Home", cityTO, countryTO,"admin@tst.com","Tst","Lst", "071-2235",0);
        userTO.setUserProfileTO(profileTO);

        try {
            savedUser = userDao.saveUser(userTO);
        } catch (Exception e) {

        }

        assertNotNull(savedUser);
    }


    @Test
    public void getAllUsersTest() {

           assertNotNull(userDao.getAllUsers());
    }

}
