package com.bookstore.app.data.manager.impl;

import com.bookstore.app.commons.bo.UserBO;
import com.bookstore.app.data.dao.IUserDao;
import com.bookstore.app.data.manager.IUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by cdinu on 11/13/2015.
 */
@Component
public class UserManagerImpl implements IUserManager {


    @Autowired
    private IUserDao userDao;

    public void login(UserBO userBO) {
        userDao.login(userBO);
    }
}
