package com.bookstore.app.pages.user;

import com.bookstore.app.data.PhoneNumber;
import com.bookstore.app.data.UserProfile;
import com.bookstore.app.pages.PhoneNumberConverter;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.validation.validator.RangeValidator;

import java.util.Arrays;

public class UserProfilePage extends WebPage {

	public UserProfilePage() {
		UserProfile userProfile = new UserProfile();
		CompoundPropertyModel userProfileModel = new CompoundPropertyModel(
				userProfile);
		final Form form = new UserProfileForm("userProfile", userProfileModel);

		add(form);
		TextField userNameComp = new TextField("name");
		userNameComp.setRequired(true);
		TextField addressComp = new TextField("address");
		addressComp.setRequired(true);
		TextField cityComp = new TextField("city");
		/*
		 * Corresponding to HTML Select, we have a DropDownChoice component in
		 * Wicket. The constructor passes in the component ID "country" (that
		 * maps to wicket:id in the HTML template) as usual and along with it a
		 * list for the DropDownChoice component to render
		 */
		DropDownChoice countriesComp = new DropDownChoice("country", Arrays
				.asList(new String[] { "India", "US", "UK" }));
		TextField pinComp = new TextField("pin");
		pinComp.setRequired(true);
		pinComp.add(RangeValidator.range(0,99));
		pinComp.setType(int.class);

		TextField phoneComp = new TextField("phoneNumber",PhoneNumber.class){
			public IConverter getConverter() {
				return new PhoneNumberConverter();
			}
		};

		form.add(userNameComp);
		form.add(addressComp);
		form.add(cityComp);
		form.add(countriesComp);
		form.add(pinComp);
		form.add(phoneComp);
	}

	class UserProfileForm extends Form {
		// PropertyModel is an IModel implementation
		public UserProfileForm(String id, IModel model) {
			super(id, model);
		}

		@Override
		public void onSubmit() {
			/* Print the contents of its own model object */
			System.out.println(getModelObject());
		}
	}
}