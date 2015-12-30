package com.bookstore.app.pages.panel;

import com.bookstore.app.application.BookStoreApplication;
import com.bookstore.app.manager.UserServicesManager;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

/**
 * Created by cdinu on 12/30/2015.
 */
public class CreateAccountPanel extends Panel {

    private String userId;

    private String password;

    private String confirmPassword;

    private UserServicesManager userServicesManager;

    public CreateAccountPanel(String id) {
        super(id);
        init();
    }

    private void init(){
        FeedbackPanel feedback = new FeedbackPanel("feedback");
        Form form = new CreateAccountForm("createAccountForm");
        form.add(new TextField("userId", new PropertyModel(this, "userId")).setRequired(true));
        PasswordTextField password = (PasswordTextField) new PasswordTextField("password", new PropertyModel(this, "password"));
        password.setResetPassword(false);
        form.add(password);
        PasswordTextField confirmPassword = (PasswordTextField) new PasswordTextField("confirmPassword", new PropertyModel(this, "confirmPassword")).setRequired(true);
        confirmPassword.setResetPassword(false);
        form.add(confirmPassword);
        form.add(new EqualPasswordInputValidator(password, confirmPassword));
        add(form);
        add(feedback);
    }

    class CreateAccountForm extends Form {
        public CreateAccountForm(String id) {
            super(id);
        }

        @Override
        protected void onSubmit() {
            super.onSubmit();
            getUserServiceManager().createUser(userId,password);
        }
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public UserServicesManager getUserServiceManager() {
        BookStoreApplication application = (BookStoreApplication) getApplication();
        return application.getUserServicesManager();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
