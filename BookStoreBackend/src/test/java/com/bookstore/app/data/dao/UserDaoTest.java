package com.bookstore.app.data.dao;

import com.bookstore.app.BookStoreConfig;
import com.bookstore.app.commons.bo.UserBO;
import com.bookstore.app.data.dao.impl.UserDaoImpl;
import com.bookstore.app.data.entites.User;
import com.bookstore.app.utils.IUtil;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cdinu on 11/13/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BookStoreConfig.class)
public class UserDaoTest {

    private List<Class> configurationClassList;
    private SessionFactory sessionFactory;
    private Configuration config;

    @Autowired
    private UserDaoImpl userDao;


    @Test
    public void testLogin() {

        UserBO userBO = new UserBO();
        userBO.setUserName("user");
        userBO.setPassword("user");

        try {
             userDao.login(userBO);
        } catch (Exception e) {

        }
    }

}
