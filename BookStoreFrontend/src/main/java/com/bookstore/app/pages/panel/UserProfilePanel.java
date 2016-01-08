package com.bookstore.app.pages.panel;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.RangeValidator;

import com.bookstore.app.commons.bo.CityTO;
import com.bookstore.app.commons.bo.CountryTO;
import com.bookstore.app.commons.bo.UserProfileTO;
import com.bookstore.app.commons.bo.UserTO;
import com.bookstore.app.data.PhoneNumber;
import com.bookstore.app.data.PhoneNumberConverter;
import com.bookstore.app.manager.UserServicesManager;
import com.bookstore.app.model.UserProfileFormData;
import com.bookstore.app.pages.user.AccountPage;
import com.bookstore.app.session.BookStoreSession;

/**
 * Created by cdinu on 12/30/2015.
 */
public class UserProfilePanel extends Panel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UserTO userTO;
	
	UserProfileFormData userProfileFormData;
	
	private UserServicesManager userServicesManager;

    public UserProfilePanel(String id, UserTO userTO) {
        super(id);
        this.userTO = userTO;
        init();
    }
    
    private CountryTO selectedCountryTO;

    @SuppressWarnings({ "unchecked", "rawtypes" })
	private void init(){
    	userServicesManager = new UserServicesManager();
    	
        userProfileFormData = new UserProfileFormData();
      
		CompoundPropertyModel userProfileModel = new CompoundPropertyModel(
				userProfileFormData);
        final Form form = new UserProfileForm("userProfile", userProfileModel);

        add(form);
        add(new FeedbackPanel("feedback"));
        TextField firstNameField = new TextField("firstName");
        firstNameField.setRequired(true);
        TextField lastNameField = new TextField("lastName");
        lastNameField.setRequired(true);
        TextField emailAddressField = new TextField("emailAddress");
        emailAddressField.setRequired(true);
        emailAddressField.add(EmailAddressValidator.getInstance());
        TextField addressField = new TextField("address");
        addressField.setRequired(true);
              		
		final DropDownChoice<CountryTO> countriesDropDownField = new DropDownChoice("countrySelected",
				new PropertyModel<CountryTO>(userProfileModel, "countrySelected"), userServicesManager.getAllCountries(), new ChoiceRenderer("name", "id"));

		IModel<List<CityTO>> citiesChoices = new AbstractReadOnlyModel<List<CityTO>>()
		{
			@Override
			public List<CityTO> getObject()
			{
				List<CityTO> allCitiesString = new ArrayList<CityTO>();
				CountryTO selectedCountry = userProfileFormData.getCountrySelected();
				
				if(selectedCountry != null){
					
					for (CityTO cityTO : userServicesManager.getCitiesByCountryId(selectedCountry.getId())) {
						allCitiesString.add(cityTO);
					}	
				}
				
				return allCitiesString;
			}
		};
		countriesDropDownField.setRequired(true);
		
		final DropDownChoice<CityTO> citiesDropDownFiled = new DropDownChoice("citySelected", new PropertyModel<CountryTO>(userProfileModel, "citySelected"), citiesChoices,
				new ChoiceRenderer("name", "id"));
		citiesDropDownFiled.setOutputMarkupId(true);
		citiesDropDownFiled.setRequired(true);
	   
		countriesDropDownField.add(new AjaxFormComponentUpdatingBehavior("onchange") {
			
				@Override
				protected void onUpdate(AjaxRequestTarget target) {
					System.out.println("update");
					target.add(citiesDropDownFiled);
					
				}
			});
	    
	    
        TextField pinFiled = new TextField("pin");
        pinFiled.setRequired(true);
        pinFiled.add( new RangeValidator<Integer>(0, 99));
        pinFiled.setType(int.class);

        TextField phoneFiled = new TextField("phoneNumber");
//       	TextField phoneComp = new TextField("phoneNumber",PhoneNumber.class){
//            /**
//			 * 
//			 */
//			private static final long serialVersionUID = 1L;
//
//			public IConverter getConverter() {
//				System.out.println("here");
//                return new PhoneNumberConverter();
//            }
//        };
        
        Button submitButton = new Button("submitButton") {    
            @Override
            public void onSubmit() {
            	UserTO userTO = getUserTO();
            	UserProfileTO newUserProfileTO = new UserProfileTO();
            	if(userTO.getUserProfileTO() != null && userTO.getUserProfileTO().getId()!= null){
            		newUserProfileTO.setId(userTO.getUserProfileTO().getId());
            	}
            	newUserProfileTO.setFirstName(userProfileFormData.getFirstName());
            	newUserProfileTO.setLastName(userProfileFormData.getLastName());
            	newUserProfileTO.setAddress(userProfileFormData.getAddress());
            	newUserProfileTO.setCountryTO(userProfileFormData.getCountrySelected());
            	newUserProfileTO.setCityTO(userProfileFormData.getCitySelected());
            	newUserProfileTO.setPin(userProfileFormData.getPin());
            	newUserProfileTO.setPhoneNumber(userProfileFormData.getPhoneNumber());
            	newUserProfileTO.setEmailAddress(userProfileFormData.getEmailAddress());
            	
            	
            	userTO.setUserProfileTO(newUserProfileTO);
            	
            	UserTO savedUser = userServicesManager.saveUser(userTO);
            	
            	if(savedUser!=null){
            		
            		BookStoreSession session = (BookStoreSession)getSession();
            		session.setUser(savedUser);
            	}else{
            		//error
            	}
            	
            	
                System.out.println("OnSubmit");
            }
        };


        form.add(firstNameField);
        form.add(lastNameField);
        form.add(emailAddressField);
        form.add(addressField);
        form.add(countriesDropDownField);
        form.add(citiesDropDownFiled);
        form.add(pinFiled);
        form.add(phoneFiled);
        form.add(submitButton);
        
    
    }


    public UserTO getUserTO() {
        return userTO;
    }

    public void setUserTO(UserTO userTO) {
        this.userTO = userTO;
    }

	public CountryTO getSelectedCountryTO() {
		return selectedCountryTO;
	}

	public void setSelectedCountryTO(CountryTO selectedCountryTO) {
		this.selectedCountryTO = selectedCountryTO;
	}

	private class CountriesDropDownChoice extends DropDownChoice {

        
		public CountriesDropDownChoice(String id, IModel model, List displayData, IChoiceRenderer renderer) {
			super(id, model, displayData, renderer);
		}

	    // Indicate that you want a server-side notification
        // when the user changes the drop-down selection.
        public boolean wantOnSelectionChangedNotifications() {
            return true;
        }

        public void onSelectionChanged(Object newSelection) {
		
        }
    	
    } 
	
	private class UserProfileForm extends Form{

		public UserProfileForm(String id, IModel model) {
			super(id, model);
			// TODO Auto-generated constructor stub
		}
		
		 @Override
	        public void onSubmit() {
             setResponsePage(new AccountPage());
	        }
		
	}
}
