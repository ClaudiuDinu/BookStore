package com.bookstore.app.data.entites;

import com.bookstore.app.commons.bo.UserTO;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by cdinu on 11/13/2015.
 */
@Entity(name="USER")
public class User  implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false)
    private Long id;

    @Basic
    @Column(name = "USER_NAME")
    private String userName;

    @Basic
    @Column(name = "PASSWORD")
    private String password;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private UserProfile userProfile;

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
    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public UserTO asTO(){
        return new UserTO(getId(), getUserName(), getPassword(), getUserProfile()!= null ? getUserProfile().asTO(): null);
    }


}
