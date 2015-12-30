package com.bookstore.app.commons.bo;

/**
 * Created by cdinu on 12/14/2015.
 */
public class CountryTO {
    private Long id;

    private String name;

    public CountryTO() {
    }

    public CountryTO(Long id, String name) {
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

}
