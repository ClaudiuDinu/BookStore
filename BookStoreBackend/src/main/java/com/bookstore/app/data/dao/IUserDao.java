package com.bookstore.app.data.dao;

import com.bookstore.app.commons.bo.CityTO;
import com.bookstore.app.commons.bo.CountryTO;
import com.bookstore.app.commons.bo.UserTO;
import com.bookstore.app.commons.exceptions.SavingObjectException;
import com.bookstore.app.commons.exceptions.UserAuthenticationException;

import java.util.List;

/**
 * Created by cdinu on 11/13/2015.
 */
public interface IUserDao {
    public UserTO login(String userName, String password) throws UserAuthenticationException;

    public UserTO getUserByName(String userName);

    public UserTO getUserById(int userId);

    public List<UserTO> getAllUsers();

    public UserTO saveUser(UserTO userTO) throws SavingObjectException;
    
    public List<CountryTO> getAllCountries();
    
    public List<CityTO> getCitiesByCountryId(Long countryId);
}
