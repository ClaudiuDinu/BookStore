package com.bookstore.app.pages.user;

import com.bookstore.app.pages.layout.BookstoreTemplate;
import com.bookstore.app.pages.panel.AccountPanel;
import com.bookstore.app.pages.panel.CreateAccountPanel;

/**
 * Created by cdinu on 12/30/2015.
 */
public class AccountPage extends BookstoreTemplate {

    public AccountPage() {
        super();
        replace(new AccountPanel(CONTENT_ID));
        getMenuPanel().setVisible(false);
    }
}
