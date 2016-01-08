package com.bookstore.app.pages.panel;

import com.bookstore.app.commons.bo.UserTO;
import com.bookstore.app.data.UserProfile;
import com.bookstore.app.pages.user.UserProfilePage;
import com.bookstore.app.session.BookStoreSession;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

/**
 * Created by cdinu on 12/30/2015.
 */
public class AccountPanel extends Panel {

    private UserTO userTO;

    public AccountPanel(String id) {
        super(id);
       
        userTO = ((BookStoreSession)getSession()).getUser();
        init();
    }

    private void init(){
        add(new Label("message", new PropertyModel<Object>(this, "userTO.userName")));
        add(new Label("userFirstName", new PropertyModel<Object>(this, "userTO.userProfileTO.firstName")));
        add(new Label("userLastName", new PropertyModel<Object>(this, "userTO.userProfileTO.lastName")));
        add(new Label("userAddress", new PropertyModel<Object>(this, "userTO.userProfileTO.address")));
        add(new Label("userCity", new PropertyModel<Object>(this, "userTO.userProfileTO.cityTO.name")));
        add(new Label("userCountry", new PropertyModel<Object>(this, "userTO.userProfileTO.countryTO.name")));
        add(new Label("userEmailAddress", new PropertyModel<Object>(this, "userTO.userProfileTO.emailAddress")));
        add(new Label("userPhoneNumber", new PropertyModel<Object>(this, "userTO.userProfileTO.phoneNumber")));

        add(new Link("changeUserInformation") {
            @Override
            public void onClick() {
                setResponsePage(UserProfilePage.class);
            }
        });
    }

    public UserTO getUserTO() {
        return userTO;
    }

    public void setUserTO(UserTO userTO) {
        this.userTO = userTO;
    }
}
