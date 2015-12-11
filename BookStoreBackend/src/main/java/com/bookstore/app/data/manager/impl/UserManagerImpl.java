package com.bookstore.app.data.manager.impl;

import com.bookstore.app.commons.bo.UserTO;
import com.bookstore.app.commons.exceptions.UserAuthenticationException;
import com.bookstore.app.data.dao.IUserDao;
import com.bookstore.app.data.manager.IUserManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by cdinu on 11/13/2015.
 */
@Component
@Qualifier("userManager")
public class UserManagerImpl implements IUserManager {


    @Resource
    @Qualifier("userDao")
    private IUserDao userDao;

    public UserTO login(UserTO userTO) throws UserAuthenticationException{
       return userDao.login(userTO);
    }

    public UserTO getUserByName(String userName) {
        return userDao.getUserByName(userName);
    }

    public List<UserTO> getAllUsers() {
        return userDao.getAllUsers();
    }
}
