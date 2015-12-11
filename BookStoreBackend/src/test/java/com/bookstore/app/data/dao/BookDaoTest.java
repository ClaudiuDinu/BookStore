package com.bookstore.app.data.dao;


import com.bookstore.app.commons.bo.BookTO;
import com.bookstore.app.data.dao.impl.BookDaoImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Created by cdinu on 12/7/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/appServlet/servlet-context.xml")
public class BookDaoTest {

    @Resource
    @Qualifier("bookDao")
    private BookDaoImpl bookDao;


    @Test
    public void getAllBooksTest(){

        List<BookTO> allBooks = bookDao.findAllBooks();

        System.out.println("Size: " + allBooks.size());
        assertNotNull(allBooks);

    }

    @Test
    public void getBookTest(){

        BookTO bookTO = bookDao.getBook(1);

        assertNotNull(bookTO);

    }


    @Test
    public void getAllBookForCategoryTest(){

        List<BookTO> booksForCategory = bookDao.getAllBooksForCategory(1);

        System.out.println("Size: " + booksForCategory.size());
        assertNotNull(booksForCategory);

    }

    @Test
    public void getBookForCategoryTest(){

        int categoryId = 0;
        int start = 0;
        int count= 3;

        List<BookTO> booksForCategory = bookDao.getBooksForCategory(categoryId,start,count);

        System.out.println("Size: " + booksForCategory.size());
        assertNotNull(booksForCategory);

    }
}
