package com.bookstore.app.spring.controller;

import com.bookstore.app.commons.bo.BookTO;
import com.bookstore.app.commons.bo.CityTO;
import com.bookstore.app.commons.bo.CountryTO;
import com.bookstore.app.commons.bo.UserTO;
import com.bookstore.app.commons.exceptions.SavingObjectException;
import com.bookstore.app.commons.exceptions.UserAuthenticationException;
import com.bookstore.app.commons.rest.RestURIConstants;
import com.bookstore.app.data.manager.IBookManager;
import com.bookstore.app.data.manager.IUserManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by cdinu on 11/26/2015.
 */
@RestController
public class RestServiceController  implements RestURIConstants{

    @Resource
    @Qualifier("userManager")
    private IUserManager userManager;

    @Resource
    @Qualifier("bookManager")
    private IBookManager bookManager;

    @RequestMapping(value = REST_USER_DUMMY, method = RequestMethod.GET)
    public UserTO getDummyUser(){
        return new UserTO(null,"DummyName", "pass", null);
    }

    @RequestMapping(value =REST_USER_ALL, method = RequestMethod.GET)
    public List<UserTO> getAllUsers(){
       return  userManager.getAllUsers();
    }

    @RequestMapping(value = REST_USER_LOGIN, method = RequestMethod.GET)
    public UserTO login(@RequestParam("userName") String userName, @RequestParam("password") String password ){
        UserTO loggedUserTO = null;
        try {
            loggedUserTO =   userManager.login(userName,password);
        }catch (UserAuthenticationException e){

        }
       return loggedUserTO;
    }

    @RequestMapping(value = REST_USER_ADD, method = RequestMethod.POST)
    public void saveUser(@RequestBody UserTO userTO){
        try {
            userManager.saveUser(userTO);
        } catch (SavingObjectException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = REST_BOOK_ADD, method = RequestMethod.POST)
    public void addBook(@RequestBody BookTO bookTO){
        try {
            bookManager.addBook(bookTO);
        } catch (SavingObjectException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = REST_BOOK_ALL, method = RequestMethod.GET)
    public List<BookTO> getAllBooks(){
        return bookManager.getAllBooks();
    }

    @RequestMapping(value = REST_BOOK_FIND_BY_ID, method = RequestMethod.GET)
    public BookTO getBook(@PathVariable("id") int id){
        return bookManager.getBook(id);
    }

    @RequestMapping(value = REST_BOOK_FIND_ALL_FOR_CATEGORY, method = RequestMethod.GET)
    public List<BookTO> getAllBooksForCategory(@PathVariable("id") int id){
        return bookManager.getAllBooksForCategory(id);
    }

    @RequestMapping(value = REST_BOOK_FIND_FOR_CATEGORY, method = RequestMethod.GET)
    public List<BookTO> getBooksForCategory(@PathVariable("id") int id, @PathVariable("start") int start,@PathVariable("count") int count){
        return bookManager.getBooksForCategory(id, start, count);
    }


    @RequestMapping(value = REST_BOOK_CATEGORY_ALL, method = RequestMethod.GET)
    public List<BookTO> getAllCategory(){
        return bookManager.getAllCategories();
    }
    
    @RequestMapping(value = REST_COUNTRY_ALL, method = RequestMethod.GET)
    public List<CountryTO> getAllCountries(){
        return userManager.getAllCountries();
    }
    
    @RequestMapping(value = REST_CITIES_BY_COUNTRY_ID, method = RequestMethod.GET)
    public List<CityTO> getCitiesByCountryId(@PathVariable("countryId") Long countryId){
        return userManager.getCitiesByCountryId(countryId);
    }


}
