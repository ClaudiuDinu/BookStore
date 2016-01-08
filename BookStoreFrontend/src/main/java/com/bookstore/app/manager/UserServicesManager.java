package com.bookstore.app.manager;

import com.bookstore.app.commons.bo.CityTO;
import com.bookstore.app.commons.bo.CountryTO;
import com.bookstore.app.commons.bo.UserTO;
import com.bookstore.app.commons.rest.RestURIConstants;
import com.bookstore.app.data.BookData;
import com.bookstore.app.services.RestTemplateProvider;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.Serializable;
import java.net.URI;
import java.util.*;

/**
 * Created by cdinu on 11/27/2015.
 */
public class UserServicesManager extends RestTemplateProvider implements RestURIConstants,Serializable{


    public UserTO getDummyUser() {

       return getRestObject(REST_USER_DUMMY, UserTO.class);
    }

    public List<UserTO> getAllUsers() {

        UserTO[] userTOs = getRestObject(REST_USER_ALL, UserTO[].class);

        return new ArrayList<UserTO>(Arrays.asList(userTOs));
    }

    public UserTO login(String userName, String password){

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userName", userName);
        params.put("password", password);

        String url = createUrl(REST_USER_LOGIN,params);

        return getRestObject(url, UserTO.class);
    }

    public UserTO createUser(String userName, String password){

        UserTO userTO = new UserTO(null,userName,password,null);

        return postRestObject(REST_USER_ADD, userTO, UserTO.class);
    }
    
    public UserTO saveUser(UserTO userTO){

        return postRestObject(REST_USER_ADD, userTO, UserTO.class);
    }

    /**
     * create an URL for params that are send on GET. ex /login?userName=userName&password=password&....
     * @param uri
     * @param map
     * @return
     */
    private String createUrl(String uri,Map<String,Object> map) {

        StringBuilder sb = new StringBuilder();

        if(!map.isEmpty()){
            sb.append(uri);
            sb.append("?");
            for(Map.Entry<String,Object> entry : map.entrySet())
            {
                sb.append(entry.getKey());
                sb.append("=");
                sb.append(entry.getValue());
                sb.append("&");
            }
            sb.delete(sb.length()-1, sb.length());

        }

        return sb.toString();
    }
    
    
    public List<CountryTO> getAllCountries() {

    	CountryTO[] countryTOs = getRestObject(REST_COUNTRY_ALL, CountryTO[].class);

        return new ArrayList<CountryTO>(Arrays.asList(countryTOs));
    }

    public List<CityTO> getCitiesByCountryId(long countryId) {
    	
    	  Map<String, Object> params = new HashMap<String, Object>();
          params.put("countryId", countryId);

          CityTO[] cities = getRestObjects(REST_CITIES_BY_COUNTRY_ID, CityTO[].class, params);

          return new ArrayList<CityTO>(Arrays.asList(cities));
    	
    
    }
    
}
