package com.bookstore.app.pages;

import com.bookstore.app.pages.layout.BookstoreTemplate;
import com.bookstore.app.pages.panel.ViewBooksPanel;

public class ViewBooksPage extends BookstoreTemplate{

	public ViewBooksPage() {
		super();
		replace(new ViewBooksPanel(CONTENT_ID));
		getMenuPanel().setVisible(false);
	}
	
	

}
