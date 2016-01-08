package com.bookstore.app.pages.layout;

import com.bookstore.app.pages.user.LoginPage;
import com.bookstore.app.session.BookStoreSession;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;

public class SecuredBookstoreTamplate extends BookstoreTemplate {
   
	public SecuredBookstoreTamplate(){
        if (!isAuthorized()){
            throw new RestartResponseAtInterceptPageException(LoginPage.class);
        }
    }
    protected boolean isAuthorized() {
        BookStoreSession session = ((BookStoreSession) Session.get());
        return session == null ? false : session.isUserLoggedIn();
    }
}

