package com.bookstore.app.data.manager;

import com.bookstore.app.commons.bo.UserProfileTO;
import com.bookstore.app.commons.bo.UserTO;
import com.bookstore.app.commons.exceptions.SavingObjectException;
import com.bookstore.app.commons.exceptions.UserAuthenticationException;

import java.util.List;

/**
 * Created by cdinu on 11/13/2015.
 */
public interface IUserManager {

    public UserTO login(String userName, String password) throws UserAuthenticationException;

    public UserTO getUserByName(String userName);

    public UserTO getUserById(int userId);

    public List<UserTO> getAllUsers();

    public UserTO saveUser(UserTO userTO) throws SavingObjectException;

}
