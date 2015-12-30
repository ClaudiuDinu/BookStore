package com.bookstore.app.commons.bo;

/**
 * Created by cdinu on 12/14/2015.
 */
public class CityTO {

    private Long id;

    private String name;

    private CountryTO country;

    public CityTO() {
    }

    public CityTO(Long id, String name, CountryTO country) {
        this.country = country;
        this.id = id;
        this.name = name;
    }

    public CountryTO getCountry() {
        return country;
    }

    public void setCountry(CountryTO country) {
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
}
