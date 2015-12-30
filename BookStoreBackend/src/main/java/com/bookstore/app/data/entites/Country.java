package com.bookstore.app.data.entites;

import com.bookstore.app.commons.bo.CountryTO;

import javax.persistence.*;

/**
 * Created by cdinu on 12/14/2015.
 */
@Entity
@Table(name="COUNTRY")
public class Country {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false)
    private Long id;

    @Basic
    @Column(name="NAME", nullable=false,updatable=false)
    private String name;

    public Country() {
    }

    public Country(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CountryTO asTO(){
        return new CountryTO(getId(),getName());
    }
}
