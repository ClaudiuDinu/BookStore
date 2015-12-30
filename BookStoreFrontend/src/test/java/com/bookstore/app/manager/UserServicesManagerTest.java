package com.bookstore.app.manager;

import static org.junit.Assert.*;
import com.bookstore.app.commons.bo.UserTO;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by cdinu on 11/27/2015.
 */

public class UserServicesManagerTest {
    private UserServicesManager userServicesManager;


    public UserServicesManagerTest(){
        userServicesManager = new UserServicesManager();
    }

    @Test
    public void getUserTest(){

       UserTO userTO =  userServicesManager.getDummyUser();

        assertNotNull(userTO);

    }


    @Test
    public void login(){

        UserTO userTO =  userServicesManager.login("admin", "admin");

        assertNotNull(userTO);

    }


}
