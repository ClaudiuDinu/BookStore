package com.bookstore.app;

import static org.junit.Assert.*;

import com.bookstore.app.data.manager.IUserManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by cdinu on 11/13/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BookStoreConfig.class)
public class BookStoreConfigTest {

    @Autowired
    private IUserManager userManager;

    @Test
    public void userShouldNotBeNULL(){
        assertNotNull(userManager);
    }
}
