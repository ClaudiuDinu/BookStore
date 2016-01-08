package com.bookstore.app.pages.user;

import com.bookstore.app.commons.bo.UserTO;
import com.bookstore.app.data.PhoneNumber;
import com.bookstore.app.data.PhoneNumberConverter;
import com.bookstore.app.data.UserProfile;
import com.bookstore.app.pages.layout.BookstoreTemplate;
import com.bookstore.app.pages.panel.UserProfilePanel;
import com.bookstore.app.session.BookStoreSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.validation.validator.RangeValidator;

import java.util.Arrays;

public class UserProfilePage extends BookstoreTemplate {

	public UserProfilePage() {
		super();

		UserTO userTO = ((BookStoreSession)getSession()).getUser();

		if (userTO == null) {
			redirectToInterceptPage(new LoginPage());
		}else{
			replace(new UserProfilePanel(CONTENT_ID, userTO));
			getMenuPanel().setVisible(false);
		}
	}

}