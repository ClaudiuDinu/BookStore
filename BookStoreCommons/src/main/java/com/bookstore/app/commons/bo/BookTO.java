package com.bookstore.app.commons.bo;

import java.io.Serializable;

/**
 * Created by cdinu on 12/7/2015.
 */
public class BookTO implements Serializable {


    private int id;

    private String title;

    private String author;

    private float price;

    private String publisher;

    private BookCategoryTO bookCategory;

    public BookTO() {
    }

    public BookTO(int id, String author, BookCategoryTO bookCategory, float price, String publisher, String title) {

        this.id =id;
        this.author = author;
        this.bookCategory = bookCategory;
        this.price = price;
        this.publisher = publisher;
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BookCategoryTO getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(BookCategoryTO bookCategory) {
        this.bookCategory = bookCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
