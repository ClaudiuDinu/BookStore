package com.bookstore.app.data.entites;

import com.bookstore.app.commons.bo.CityTO;
import com.bookstore.app.commons.bo.CountryTO;
import com.bookstore.app.commons.bo.UserProfileTO;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="USER_PROFILE")
public class UserProfile implements Serializable {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "ID", updatable = false)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_ID")
	private User user;

	@Basic
	@Column(name="FIRST_NAME")
	private String firstName;

	@Basic
	@Column(name="LAST_NAME")
	private String lastName;

	@Basic
	@Column(name="EMAIL_ADDRESS")
	private String emailAddress;

	@Basic
	@Column(name="ADDRESS")
	private String address;

	@ManyToOne
	@JoinColumn(name = "CITY_ID")
	private City city;

	@ManyToOne
	@JoinColumn(name = "COUNTRY_ID")
	private Country country;

	@Basic
	@Column(name="PIN")
	private int pin;

	@Basic
	@Column(name="PHONE_NUMBER")
	private String phoneNumber;


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserProfileTO asTO(){
		return new UserProfileTO(getId(), getAddress(), getCity().asTO(), getCountry().asTO(),getEmailAddress(),getFirstName(),  getLastName(),
				getPhoneNumber(), getPin());
	}
}
