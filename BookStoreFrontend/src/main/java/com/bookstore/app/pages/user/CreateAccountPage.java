package com.bookstore.app.pages.user;

import com.bookstore.app.pages.layout.BookstoreTemplate;
import com.bookstore.app.pages.panel.CreateAccountPanel;

/**
 * Created by cdinu on 12/30/2015.
 */
public class CreateAccountPage extends BookstoreTemplate {
    public CreateAccountPage() {
        super();
        replace(new CreateAccountPanel(CONTENT_ID));
        getMenuPanel().setVisible(false);
    }
}
