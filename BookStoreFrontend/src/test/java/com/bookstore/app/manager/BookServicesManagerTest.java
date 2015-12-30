package com.bookstore.app.manager;

import com.bookstore.app.commons.bo.BookCategoryTO;
import com.bookstore.app.data.BookData;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by cdinu on 12/11/2015.
 */
public class BookServicesManagerTest {


    @Test
    public void getAllBooksTest(){
        BookServicesManager bookServicesManager = new BookServicesManager();

        List<BookData> bookDatas =  bookServicesManager.getAllBooks();

        assertNotNull(bookDatas);
    }

    @Test
    public void getBookTest(){
        int bookId=1;
        BookServicesManager bookServicesManager = new BookServicesManager();

        BookData bookData =  bookServicesManager.getBook(bookId);

        assertNotNull(bookData);
    }

    @Test
    public void getAllBooksForCategoryTest(){

        int categoryId=1;
        BookServicesManager bookServicesManager = new BookServicesManager();

        List<BookData> bookDatas =  bookServicesManager.getAllBooksForCategory(categoryId);
        assertNotNull(bookDatas);

    }

    @Test
    public void getBooksForCategoryTest(){

        int categoryId=0;
        int start=0;
        int count=2;
        BookServicesManager bookServicesManager = new BookServicesManager();

        List<BookData> bookDatas =  bookServicesManager.getBooksForCategory(categoryId,start,count);
        assertNotNull(bookDatas);

    }

    @Test
    public void getBookCategoriesTest(){

        BookServicesManager bookServicesManager = new BookServicesManager();

        List<BookCategoryTO> categoryTOs =  bookServicesManager.getBookCategories();
        assertNotNull(categoryTOs);

    }
}
