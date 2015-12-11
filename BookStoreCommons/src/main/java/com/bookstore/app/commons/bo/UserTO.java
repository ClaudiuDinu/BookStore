package com.bookstore.app.commons.bo;

import java.io.Serializable;

/**
 * Created by cdinu on 11/13/2015.
 */
public class UserTO implements Serializable {

    private Long id;

    private String userName;

    private String password;


    public UserTO() {
        this(null, null, null);
    }

    public UserTO(Long id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
