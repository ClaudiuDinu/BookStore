package com.bookstore.app.manager;

import com.bookstore.app.commons.bo.BookCategoryTO;
import com.bookstore.app.commons.bo.BookTO;
import com.bookstore.app.commons.bo.UserTO;
import com.bookstore.app.commons.rest.RestURIConstants;
import com.bookstore.app.data.BookData;
import com.bookstore.app.services.RestTemplateProvider;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.Serializable;
import java.util.*;

/**
 * Created by cdinu on 12/8/2015.
 */
public class BookServicesManager extends RestTemplateProvider implements RestURIConstants,Serializable {

    public List<BookData> getAllBooks() {

       BookData[] bookDatas = getRestObjects(REST_BOOK_ALL, BookData[].class);

        return new ArrayList<BookData>(Arrays.asList(bookDatas));
    }

    public List<BookData> getAllBooksForCategory(int categoryId) {

        if(categoryId==0){
            //get all books
            return getAllBooks();
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", categoryId);

        BookData[] bookDatas = getRestObjects(REST_BOOK_FIND_ALL_FOR_CATEGORY, BookData[].class, params);

        return new ArrayList<BookData>(Arrays.asList(bookDatas));
    }

    public BookData getBook(int id) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        return (BookData)getRestObject(REST_BOOK_FIND_BY_ID, BookData.class, params);
    }

    public List<BookData> getBooksForCategory(int categoryId, int start, int count) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", categoryId);
        params.put("start", start);
        params.put("count", count);

        BookData[] bookDatas = getRestObjects(REST_BOOK_FIND_FOR_CATEGORY, BookData[].class, params);

        return new ArrayList<BookData>(Arrays.asList(bookDatas));
    }

    public List<BookCategoryTO> getBookCategories() {

        BookCategoryTO[] bookCategoryTOs = getRestObject(REST_BOOK_CATEGORY_ALL, BookCategoryTO[].class);

        return new ArrayList<BookCategoryTO>(Arrays.asList(bookCategoryTOs));
    }


}
