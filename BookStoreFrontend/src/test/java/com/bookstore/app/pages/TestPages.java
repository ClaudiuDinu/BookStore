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
package com.bookstore.app.pages;

import org.apache.wicket.util.file.File;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.bookstore.app.application.BookStoreApplication;

/**
 * Simple test using the WicketTester
 */
public class TestPages
{
	private WicketTester tester;

	@Before
	public void setUp()
	{
		File webContextDir = new File("src/main/webapp");
		tester = new WicketTester(new BookStoreApplication(), webContextDir.getAbsolutePath());
	}


	@Ignore
	public void viewBooksPageRendersSuccessfully()
	{
		//start and render the test page
		tester.startPage(ViewBooksPage.class);

		//assert rendered page class
		tester.assertRenderedPage(ViewBooksPage.class);
	}

	@Ignore
	public void checkoutPageRendersSuccessfully()
	{
		//start and render the test page
		tester.startPage(Checkout.class);

		//assert rendered page class
		tester.assertRenderedPage(Checkout.class);
	}

	@Ignore
	public void confirmationPageRendersSuccessfully()
	{
		//start and render the test page
		tester.startPage(Confirmation.class);

		//assert rendered page class
		tester.assertRenderedPage(Confirmation.class);
	}
}
