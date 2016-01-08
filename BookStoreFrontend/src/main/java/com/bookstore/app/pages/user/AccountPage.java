package com.bookstore.app.pages.user;

import com.bookstore.app.commons.bo.UserTO;
import com.bookstore.app.pages.layout.BookstoreTemplate;
import com.bookstore.app.pages.layout.SecuredBookstoreTamplate;
import com.bookstore.app.pages.panel.AccountPanel;
import com.bookstore.app.pages.panel.CreateAccountPanel;
import com.bookstore.app.session.BookStoreSession;

/**
 * Created by cdinu on 12/30/2015.
 */
public class AccountPage extends SecuredBookstoreTamplate {

    String userId;

    public AccountPage() {
        super();

        replace(new AccountPanel(CONTENT_ID));
        getMenuPanel().setVisible(false);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
