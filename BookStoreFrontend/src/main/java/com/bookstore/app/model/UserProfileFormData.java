package com.bookstore.app.model;

import java.io.Serializable;
import java.util.List;

import com.bookstore.app.commons.bo.CityTO;
import com.bookstore.app.commons.bo.CountryTO;

public class UserProfileFormData implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	private Long id;

	private String firstName;

	private String lastName;

	private String emailAddress;

	private String address;

	private CountryTO countrySelected;

	private CityTO citySelected;

	private int pin;

	private String phoneNumber;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public CountryTO getCountrySelected() {
		return countrySelected;
	}

	public void setCountrySelected(CountryTO countrySelected) {
		this.countrySelected = countrySelected;
	}

	public CityTO getCitySelected() {
		return citySelected;
	}

	public void setCitySelected(CityTO citySelected) {
		this.citySelected = citySelected;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
