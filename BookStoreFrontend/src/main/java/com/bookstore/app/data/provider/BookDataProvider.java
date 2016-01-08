package com.bookstore.app.data.provider;


import com.bookstore.app.application.BookStoreApplication;
import com.bookstore.app.commons.bo.BookCategoryTO;
import com.bookstore.app.data.BookData;
import com.bookstore.app.manager.BookServicesManager;
import com.bookstore.app.model.LoadableBookModel;
import org.apache.wicket.Application;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;


import java.util.Iterator;

public class BookDataProvider implements IDataProvider<BookData> {
	// Holds on to the current user-selected category
	// ('ALL'/'J2EE'/'Scripting')
	private BookCategoryTO category;

	public BookDataProvider(BookCategoryTO category) {
		this.category = category;
	}

	// By default display all books.
	public BookDataProvider() {
		this(new BookCategoryTO(0,null));
	}
	
	
	public Iterator<? extends BookData> iterator(int first, int count) {
		return getBookServicesManager().getBooksForCategory(category.getId(), new Long(first).intValue(), new Long(count).intValue()).iterator();
		
	}
	
	public int size() {
		return getBookServicesManager().getAllBooksForCategory(category.getId()).size();
	}
	
	@SuppressWarnings("unchecked")
	public IModel<BookData> model(BookData object) {
		return new LoadableBookModel(object);
	}

	public void detach() {
		// TODO Auto-generated method stub
	}
	
	// The BookServicesManager has to be looked up when required.
	private BookServicesManager getBookServicesManager() {
		return ((BookStoreApplication) Application.get()).getBookServicesManager();
	}
	
	public BookCategoryTO getCategory() {
		return category;
	}

	public void setCategoryId(BookCategoryTO category) {
		this.category = category;
	}

}
