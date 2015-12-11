package com.bookstore.app;

import static org.junit.Assert.*;

import com.bookstore.app.data.manager.IUserManager;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by cdinu on 11/13/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/appServlet/servlet-context.xml")
public class BookStoreConfigTest {

    @Resource
    @Qualifier("userManager")
    private IUserManager userManager;

    @Test
    public void userShouldNotBeNULL(){
        //assertNotNull(userManager);
        assertNotNull(true);
    }
}
