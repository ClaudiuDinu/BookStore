package com.bookstore.app.data.manager;

import com.bookstore.app.commons.bo.BookTO;
import com.bookstore.app.commons.exceptions.SavingObjectException;

import java.util.List;

/**
 * Created by cdinu on 12/7/2015.
 */
public interface IBookManager {

    public void addBook(BookTO book) throws SavingObjectException;

    public BookTO getBook(int id);

    public int getBookCount(int categoryId);

    public List<BookTO> getAllBooksForCategory(int categoryId);

    public List getAllBooks();

    public List getAllCategories();

    public List getBooksForCategory(int categoryId,int start,int count);
}
