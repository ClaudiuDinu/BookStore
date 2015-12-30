package com.bookstore.app.data.entites;

import com.bookstore.app.commons.bo.CityTO;

import javax.persistence.*;

/**
 * Created by cdinu on 12/14/2015.
 */
@Entity
@Table(name="CITY")
public class City {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false)
    private Long id;

    @Basic
    @Column(name="NAME", nullable=false,updatable=false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "COUNTRY_ID")
    private Country country;

    public City() {
    }

    public City( Long id, Country country, String name) {
        this.country = country;
        this.id = id;
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
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

    public CityTO asTO(){
        return new CityTO(getId(),getName(), getCountry().asTO());
    }
}
