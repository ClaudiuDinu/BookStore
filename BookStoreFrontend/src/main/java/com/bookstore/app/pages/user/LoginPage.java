package com.bookstore.app.pages.user;

import com.bookstore.app.pages.layout.BookstoreTemplate;
import com.bookstore.app.pages.panel.IndexPanel;
import com.bookstore.app.pages.panel.LoginPanel;

/**
 * Created by cdinu on 12/30/2015.
 */
public class LoginPage extends BookstoreTemplate{

    public LoginPage() {
        super();
        replace(new LoginPanel(CONTENT_ID));
        getMenuPanel().setVisible(false);

    }
}
