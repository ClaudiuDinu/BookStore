package com.bookstore.app.data.dao;

import com.bookstore.app.commons.bo.UserBO;

/**
 * Created by cdinu on 11/13/2015.
 */
public interface IUserDao {
    void login(UserBO userBO);
}
