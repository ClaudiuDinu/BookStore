package com.bookstore.app.commons.bo;

import java.io.Serializable;

/**
 * Created by cdinu on 12/14/2015.
 */
public class UserProfileTO implements Serializable {


    private Long id;

    private String firstName;

    private String lastName;

    private String emailAddress;

    private String address;

    private CityTO cityTO;

    private CountryTO countryTO;

    private int pin;

    private String phoneNumber;

    public UserProfileTO() {
    }

    public UserProfileTO(Long id, String address, CityTO cityTO, CountryTO countryTO, String emailAddress, String firstName,  String lastName, String
            phoneNumber, int pin) {

        this.id = id;
        this.address = address;
        this.cityTO = cityTO;
        this.countryTO = countryTO;
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.pin = pin;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CityTO getCityTO() {
        return cityTO;
    }

    public void setCityTO(CityTO cityTO) {
        this.cityTO = cityTO;
    }

    public CountryTO getCountryTO() {
        return countryTO;
    }

    public void setCountryTO(CountryTO countryTO) {
        this.countryTO = countryTO;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

}
