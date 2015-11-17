package com.bookstore.app.data.entites;

import com.bookstore.app.commons.bo.UserBO;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by cdinu on 11/13/2015.
 */
@Entity(name="USER")
public class User  implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false)
    private Long id;

    @Basic
    @Column(name = "USER_NAME")
    private String userName;

    @Basic
    @Column(name = "PASSWORD")
    private String password;

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

    public UserBO asBO(){
        return new UserBO(getId(),getUserName(),getPassword());
    }
}
