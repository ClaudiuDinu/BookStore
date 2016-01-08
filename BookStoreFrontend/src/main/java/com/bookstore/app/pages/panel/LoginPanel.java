package com.bookstore.app.pages.panel;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

import com.bookstore.app.commons.bo.UserTO;
import com.bookstore.app.manager.UserServicesManager;
import com.bookstore.app.pages.user.AccountPage;
import com.bookstore.app.pages.user.CreateAccountPage;
import com.bookstore.app.session.BookStoreSession;

/**
 * Created by cdinu on 12/30/2015.
 */
public class LoginPanel extends Panel {

    private UserServicesManager userServicesManager;

    private String userId;

    private String password;

    private Page prevPage;

    public LoginPanel(String id) {
        super(id);
        init();
    }


    @SuppressWarnings({ "unchecked", "rawtypes", "serial" })
	private void init(){
        userServicesManager = new UserServicesManager();

        TextField userIdField = new TextField("userId", new PropertyModel(this, "userId"));
        PasswordTextField passField = new PasswordTextField("password", new PropertyModel(this, "password"));
        Form form = new LoginForm("loginForm");
        form.add(userIdField);
        form.add(passField);
        add(form);
        add(new FeedbackPanel("feedback"));

        add(new Link("register") {
            @Override
            public void onClick() {
                setResponsePage(CreateAccountPage.class);
            }
        });

    }

    // Define your LoginForm and override onSubmit
    @SuppressWarnings("serial")
	private class LoginForm extends Form {
        public LoginForm(String id) {
            super(id);
        }

        @Override
        public void onSubmit() {

            UserTO loggedInUser =  userServicesManager.login(getUserId(), getPassword());
            if (loggedInUser != null) {

                BookStoreSession session = (BookStoreSession)getSession();
                session.setUser(loggedInUser);
                // Continue to original request if present. Else display
                //	Welcome page.
                if (prevPage != null) {
                    setResponsePage(prevPage);
                }else{
                    AccountPage accountPage = new AccountPage();
                    setResponsePage(accountPage);
                }
            } else {
                String errMsg = getLocalizer().getString(
                        "login.errors.invalidCredentials", LoginPanel.this,
                        "Unable to sign you in");

                // Register this error message with the form component.
                error(errMsg);
            }
        }
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
