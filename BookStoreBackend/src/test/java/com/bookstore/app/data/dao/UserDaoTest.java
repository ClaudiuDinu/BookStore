package com.bookstore.app.data.dao;

import com.bookstore.app.commons.bo.UserTO;
import com.bookstore.app.data.dao.impl.UserDaoImpl;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
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

        UserTO userTO = new UserTO();
        userTO.setUserName("user");
        userTO.setPassword("user");

        try {
             userDao.login(userTO);
        } catch (Exception e) {

        }
    }


    @Test
    public void getAllUsersTest() {

           assertNotNull(userDao.getAllUsers());
    }

}
