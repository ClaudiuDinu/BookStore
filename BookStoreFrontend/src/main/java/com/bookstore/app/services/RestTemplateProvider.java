package com.bookstore.app.services;

import com.bookstore.app.commons.bo.BookTO;
import com.bookstore.app.commons.rest.RestURIConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cdinu on 11/27/2015.
 */
public class RestTemplateProvider<T> implements RestURIConstants {

    public static <T> T getRestObject(String path, Class<T> responseType ){

        return getRestObject(path, responseType, null);
    };

    public static <T> T[] getRestObjects(String path, Class<T[]> responseType ){

        return getRestObjects(path, responseType, null);
    };

    public static <T> T getRestObject(String path, Class<T> responseType, Map<String, Object> urlVariables ){

        T t = null;

          if(urlVariables == null){
              urlVariables = new HashMap<String, Object>();
          }
        RestTemplate restTemplate = new RestTemplate();

          try {

              t = restTemplate.getForObject(REST_SERVICE_URI+ path, responseType, urlVariables);
          }catch (Exception e){
              System.out.println(e.getMessage());
          }

          return t;
    };

    public static <T> T[] getRestObjects(String path, Class<T[]> responseType, Map<String, Object> urlVariables ){

        T[] t = null;

        if(urlVariables == null){
            urlVariables = new HashMap<String, Object>();
        }
        RestTemplate restTemplate = new RestTemplate();

        try {

            t = restTemplate.getForObject(REST_SERVICE_URI + path, responseType, urlVariables);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return t;
    };



    public static <T> T postRestObject(String path, Object object, Class<T> responseType ){

        T t = null;

        RestTemplate restTemplate = new RestTemplate();

        try {

            t = restTemplate.postForObject(REST_SERVICE_URI + path, object, responseType);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return t;
    };

}
