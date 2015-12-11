package com.bookstore.app.data.entites;

import java.io.Serializable;

public class UserProfile implements Serializable {
	private User user;

	private String firstName;

	private String lastName;

	private String emailAddress;

	private String address;

	private String city;

	private String country;
	
	private String state;

	private int pin;
	
	private PhoneNumber phoneNumber;

	public PhoneNumber getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(PhoneNumber phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/*
             * You can return an int!
             */
	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	/* Returns a friendly representation of the UserProfile object */
	public String toString() {
		String result = " Mr " + getFirstName() + " "+ getLastName();
		result += "\n resides at " + getAddress();
		result += "\n in the city " + getCity();
		result += "\n having Pin Code " + getPin();
		result += "\n in the country " + getCountry();
		return result;
	}

	private static final long serialVersionUID = 1L;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
