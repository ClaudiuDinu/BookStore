package com.bookstore.app.data.entites;

import com.bookstore.app.commons.bo.BookCategoryTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cdinu on 12/8/2015.
 */
@Entity
@Table(name="BOOK_CATEGORY")
public class BookCategory {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ID", updatable = false)
    private int id;

    @Basic
    @Column(name="NAME", nullable=false,updatable=false)
    private String name;

    public BookCategory(){
    }

    public BookCategory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public BookCategory(BookCategoryTO bookCategoryTO) {
        super();
        this.id = bookCategoryTO.getId();
        this.name = bookCategoryTO.getName();
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

    public BookCategoryTO asTO(){
        return new BookCategoryTO(getId(), getName());
    }

}
