package com.bookstore.app.pages;

import com.bookstore.app.pages.user.LoginPage;
import com.bookstore.app.session.BookStoreSession;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;

public class SecuredBasePage extends WebPage {
    public SecuredBasePage(){
        if (!isAuthorized()){
            throw new RestartResponseAtInterceptPageException(LoginPage.class);
        }
    }
    protected boolean isAuthorized() {
        BookStoreSession session = ((BookStoreSession) Session.get());
        return session == null ? false : session.isUserLoggedIn();
    }
}

