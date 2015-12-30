package com.bookstore.app.application;


import com.bookstore.app.manager.BookServicesManager;
import com.bookstore.app.manager.UserServicesManager;
import com.bookstore.app.pages.IndexPage;
import com.bookstore.app.session.BookStoreSession;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

import java.io.Serializable;

public class BookStoreApplication extends WebApplication implements
        Serializable {

    private BookServicesManager bookServicesManager;
    private UserServicesManager userServicesManager;

    public BookStoreApplication() {
        // Instantiate the only instance of BookDao.
        bookServicesManager = new BookServicesManager();
        userServicesManager = new UserServicesManager();
    }

    @Override
    public void init() {
        super.init();

//        getResourceSettings().getResourceFinders().add(
//                new WebApplicationPath(getServletContext(), "/"));

    }


    @Override
    public Class<? extends WebPage> getHomePage() {
        return IndexPage.class;
    }

    @Override
    public Session newSession(Request request, Response response) {
        return new BookStoreSession(request);
    }

    public BookServicesManager getBookServicesManager() {
        return bookServicesManager;
    }

    public UserServicesManager getUserServicesManager() {
        return userServicesManager;
    }
}
