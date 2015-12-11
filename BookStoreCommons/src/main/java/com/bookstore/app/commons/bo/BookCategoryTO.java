package com.bookstore.app.commons.bo;

import java.io.Serializable;

/**
 * Created by cdinu on 12/8/2015.
 */
public class BookCategoryTO implements Serializable {

    private int id;

    private String name;

    public BookCategoryTO() {}

    public BookCategoryTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
