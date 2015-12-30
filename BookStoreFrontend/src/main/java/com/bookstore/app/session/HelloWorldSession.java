package com.bookstore.app.session;


import com.bookstore.app.commons.bo.UserTO;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;


public class HelloWorldSession extends WebSession {
	private UserTO user;

	/** WebSession needs a reference to the Application class. * */
	public HelloWorldSession(Request request) {
		super(request);
	}

	public void setUser(UserTO user) {
		this.user = user;
	}

	public UserTO getUser() {
		return this.user;
	}

	// A helper to determine whether the user is logged in
	public boolean isUserLoggedIn() {
		return (user != null);
	}
}
