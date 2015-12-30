package com.bookstore.app.commons.rest;

/**
 * Created by cdinu on 12/7/2015.
 */
public interface RestURIConstants {
    public static final String REST_SERVICE_URI = "http://localhost:8081/BookStoreBackend";
    public static final String REST_USER_DUMMY = "/rest/emp/dummy";
    public static final String REST_USER_ALL = "/rest/user/all";
    public static final String REST_USER_LOGIN = "/rest/user/login";
    public static final String REST_USER_LOGIN2 = "/rest/user/login/{userName}/{password}";
    public static final String REST_USER_ADD = "/rest/user/add";

    public static final String REST_BOOK_ALL = "/rest/book/all";
    public static final String REST_BOOK_ADD = "/rest/book/add";
    public static final String REST_BOOK_FIND_BY_ID = "/rest/book/{id}";
    public static final String REST_BOOK_CATEGORY_ALL = "/rest/book/category/all";
    public static final String REST_BOOK_FIND_ALL_FOR_CATEGORY = "/rest/book/all/category/{id}";
    public static final String REST_BOOK_FIND_FOR_CATEGORY = "/rest/book/category/{id}/{start}/{count}";



}
