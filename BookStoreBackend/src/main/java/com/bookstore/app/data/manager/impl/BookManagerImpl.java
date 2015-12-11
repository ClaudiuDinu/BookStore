package com.bookstore.app.data.manager.impl;

import com.bookstore.app.commons.bo.BookTO;
import com.bookstore.app.commons.exceptions.SavingObjectException;
import com.bookstore.app.data.dao.IBookDao;
import com.bookstore.app.data.manager.IBookManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by cdinu on 12/7/2015.
 */
@Component
@Qualifier("bookManager")
public class BookManagerImpl implements IBookManager {

    @Resource
    @Qualifier("bookDao")
    private IBookDao bookDao;

    public void addBook(BookTO bookTO) throws SavingObjectException {
        bookDao.addBook(bookTO);

    }

    public List getAllBooks() {
        return bookDao.findAllBooks();
    }

    public List<BookTO> getAllBooksForCategory(int categoryId) {
        return bookDao.getAllBooksForCategory(categoryId);
    }

    public BookTO getBook(int id) {
        return bookDao.getBook(id);
    }

    public int getBookCount(int categoryId) {
        return bookDao.getBookCount(categoryId);
    }

    public List getBooksForCategory(int categoryId, int start, int count) {
        return bookDao.getBooksForCategory(categoryId,start,count);
    }

    public List getAllCategories() {
        return bookDao.getAllCategories();
    }
}
