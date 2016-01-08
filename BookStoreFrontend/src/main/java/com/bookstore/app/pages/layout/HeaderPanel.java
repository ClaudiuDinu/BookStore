/**
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.bookstore.app.pages.layout;

import java.util.Locale;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.http.WebRequest;

import com.bookstore.app.application.BookStoreApplication;
import com.bookstore.app.pages.IndexPage;
import com.bookstore.app.pages.ViewBooksPage;
import com.bookstore.app.pages.user.AccountPage;
import com.bookstore.app.pages.user.LoginPage;

public class HeaderPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings({ "rawtypes", "serial" })
	public HeaderPanel(String id) {
		super(id);
		
		add(new LocaleDropDownChoice("localeSelect"));

		// Link to return to default locale
		add(new Link<Void>("defaultLocaleLink")
		{
			@Override
			public void onClick()
			{
				WebRequest request = (WebRequest)getRequest();
				setLocale(request.getLocale());
			}
		});

		add(new Link("linkLogin") {
			@Override
			public void onClick() {
				setResponsePage(LoginPage.class);
			}
		});

		add(new Link("linkAccount") {
			@Override
			public void onClick() {
				setResponsePage(AccountPage.class);
			}
		});
		
		add(new Link("linkViewBooks") {
			@Override
			public void onClick() {
				setResponsePage(ViewBooksPage.class);
			}
		});
	}
	
	public void setLocale(Locale locale)
	{
		if (locale != null)
		{
			getSession().setLocale(locale);
		}
	}
	
	/**
	 * Dropdown with Locales.
	 */
	private final class LocaleDropDownChoice extends DropDownChoice<Locale>
	{
		/**
		 * Construct.
		 * 
		 * @param id
		 *            component id
		 */
		public LocaleDropDownChoice(String id)
		{
			super(id, BookStoreApplication.LOCALES, new LocaleChoiceRenderer());

			// set the model that gets the current locale, and that is used for
			// updating the current locale to property 'locale' of FormInput
			setModel(new PropertyModel<Locale>(HeaderPanel.this, "locale"));
		}

		/**
		 * @see org.apache.wicket.markup.html.form.DropDownChoice#onSelectionChanged(java.lang.Object)
		 */
		@Override
		public void onSelectionChanged(Locale newSelection)
		{
			// note that we don't have to do anything here, as our property
			// model allready calls setLocale when the model is
			// updated

			// force re-render by setting the page to render to the bookmarkable
			// instance, so that the page will be rendered from scratch,
			// re-evaluating the input patterns etc
			//setResponsePage(IndexPage.class);
		}

		/**
		 * @see org.apache.wicket.markup.html.form.DropDownChoice#wantOnSelectionChangedNotifications()
		 */
		@Override
		protected boolean wantOnSelectionChangedNotifications()
		{
			// we want roundtrips when a the user selects another item
			return true;
		}
	}
	
	private final class LocaleChoiceRenderer extends ChoiceRenderer<Locale>
	{
		/**
		 * Constructor.
		 */
		public LocaleChoiceRenderer()
		{
		}

		/**
		 * @see org.apache.wicket.markup.html.form.IChoiceRenderer#getDisplayValue(Object)
		 */
		@Override
		public Object getDisplayValue(Locale locale)
		{
			return locale.getDisplayName(getLocale());
		}
	}

}
