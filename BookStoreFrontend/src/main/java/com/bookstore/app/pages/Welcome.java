package com.bookstore.app.pages;


import com.bookstore.app.pages.user.UserProfilePage;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class Welcome extends WebPage {
	private String userId;
	private Page prevPage;
	public Welcome() {
		add(new Label("message", new PropertyModel<Object>(this, "userId")));
		add(new Link("linkBooks") {
			@Override
			public void onClick() {
				setResponsePage(ViewBooks.class);
			}
		});

		add(new Link("linkUSerProfile") {
			@Override
			public void onClick() {
				setResponsePage(UserProfilePage.class);
			}
		});

		add(new Link("linkLogout") {
			@Override
			public void onClick() {
				getSession().invalidate();
				setResponsePage(getApplication().getHomePage());
			}
		});
	}

	public Welcome(PageParameters params) {
		this();
		/*
		 * PageParameters class has methods to get to the parameter value when
		 * supplied with the key.
		 */
		setUserId( params.get("userId").toString());
	}
	public Welcome(String userId, Page prevPage){
	       this();
	       this.userId = userId;
	       this.prevPage = prevPage;
    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}