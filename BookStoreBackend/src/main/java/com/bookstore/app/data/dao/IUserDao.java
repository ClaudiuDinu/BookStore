package com.bookstore.app.data.dao;

import com.bookstore.app.commons.bo.UserTO;
import com.bookstore.app.commons.exceptions.UserAuthenticationException;

import java.util.List;

/**
 * Created by cdinu on 11/13/2015.
 */
public interface IUserDao {
    public UserTO login(UserTO userTO) throws UserAuthenticationException;

    public UserTO getUserByName(String userName);

    public List<UserTO> getAllUsers();
}
