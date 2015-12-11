package com.bookstore.app.data.dao.impl;

import com.bookstore.app.commons.bo.BookCategoryTO;
import com.bookstore.app.commons.bo.BookTO;
import com.bookstore.app.commons.exceptions.SavingObjectException;
import com.bookstore.app.data.dao.IBookDao;
import com.bookstore.app.data.entites.Book;
import com.bookstore.app.data.entites.BookCategory;
import com.bookstore.app.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by cdinu on 12/7/2015.
 */
@Component
@Qualifier("bookDao")
public class BookDaoImpl implements IBookDao {

    /**
     * This is the Hibernate locale session that allow you to connect to
     * database.
     */
    private SessionFactory sessionFactory;

    /**
     * The logging object.
     */
    private static Logger logger = Logger.getLogger(BookDaoImpl.class);


    public void addBook(BookTO bookTO) throws SavingObjectException{

        Book newBook = new Book();
        newBook.setAuthor(bookTO.getAuthor());
        newBook.setBookCategory(new BookCategory(bookTO.getBookCategory()));
        newBook.setPrice(bookTO.getPrice());
        newBook.setPublisher(bookTO.getPublisher());
        newBook.setTitle(bookTO.getTitle());

        Session session = getSessionFactory().openSession();

        try {
            session.beginTransaction();
            session.save(newBook);

        } catch (Exception e) {
            session.getTransaction().rollback();
            logger.error(
                    "An exception occurred while saving a user to database."
                            + HibernateUtil.TRANSACTION_ROLLBACK, e);
            throw new SavingObjectException(e.getMessage(), e);

        } finally {
            session.getTransaction().commit();
            session.close();
            logger.info(HibernateUtil.TRANSACTION_COMMITED_SUCCESSFULLY + " "
                    + HibernateUtil.SESSION_STOP);
        }


    }

    public BookTO getBook(int id) {
        Session session = getSessionFactory().openSession();

        BookTO book = null;

        try {
            session.beginTransaction();

            Criteria criteria = session.createCriteria(Book.class).add(Restrictions.eq("id", id));
            List<Book> dbBooks = criteria.list();

            if(dbBooks.size()>0){
                book = dbBooks.get(0).asTO();
            }


        }catch (Exception e){
            logger.error(
                    "An exception occurred while getting all books from database."
                            +  e);
        }finally {
            session.close();
        }

        return  book;
    }

    public int getBookCount(int categoryId) {
        if(categoryId==0){
            return  findAllBooks().size();
        }else {
            return getAllBooksForCategory(categoryId).size();
        }
    }

    public List<BookTO> getAllBooksForCategory(int categoryId) {
        Session session = getSessionFactory().openSession();
        if(categoryId==0) {
            return findAllBooks();
        }

        List<BookTO> allBooks = new ArrayList<BookTO>();

        try {
            session.beginTransaction();
            BookCategory bookCategory = new BookCategory(categoryId, null);
            Criteria criteria = session.createCriteria(Book.class).add(Restrictions.eq("bookCategory", bookCategory));
            List<Book> dbBooks = criteria.list();

            for (Book dbBook : dbBooks){
                allBooks.add(dbBook.asTO());
            }

        }catch (Exception e){
            logger.error(
                    "An exception occurred while getting all books from database."
                            +  e);
        }finally {
            session.close();
        }

        return  allBooks;
    }

    public List findAllBooks() {
        Session session = getSessionFactory().openSession();

        List<BookTO> allBooks = new ArrayList<BookTO>();

        try {
            session.beginTransaction();

            Criteria criteria = session.createCriteria(Book.class);
            List<Book> dbBooks = criteria.list();

            for (Book dbBook : dbBooks){
                allBooks.add(dbBook.asTO());
            }

        }catch (Exception e){
            logger.error(
                    "An exception occurred while getting all books from database."
                            +  e);
        }finally {
            session.close();
        }

        return  allBooks;
    }

    public List getAllCategories() {
        Session session = getSessionFactory().openSession();

        List<BookCategoryTO> allBookCategories = new ArrayList<BookCategoryTO>();

        try {
            session.beginTransaction();

            Criteria criteria = session.createCriteria(BookCategory.class);
            List<BookCategory> dbBookCategories = criteria.list();

            for (BookCategory dbBookCategory : dbBookCategories){
                allBookCategories.add(dbBookCategory.asTO());
            }

        }catch (Exception e){
            logger.error(
                    "An exception occurred while getting all book categories from database."
                            +  e);
        }finally {
            session.close();
        }

        return  allBookCategories;
    }

    public List getBooksForCategory(int categoryId, int start, int count) {
        List<BookTO> books = getAllBooksForCategory(categoryId);

        int end = start+count;
        if(books.size()<end){
            return books;
        }else{
            return  books.subList(start,end);
        }

    }

    /**
     * This method verify if a <code>SessionFactory</code> object exist
     * otherwise it is generate and return it.
     *
     * @return a <code>SessionFactory</code> object
     */
    public SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
            sessionFactory = HibernateUtil.getSessionFactory();
        }

        return sessionFactory;
    }

    /**
     * This method set a Hibernate session to a local session.
     *
     * @param sessionFactory
     *            the Hibernate session
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
