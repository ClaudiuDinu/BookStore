package com.bookstore.app.model;

// Other imports


import com.bookstore.app.application.BookStoreApplication;
import com.bookstore.app.commons.bo.BookTO;
import com.bookstore.app.manager.BookServicesManager;
import org.apache.wicket.Application;
import org.apache.wicket.model.LoadableDetachableModel;

public class LoadableBookModel extends LoadableDetachableModel {
	// Required minimal information to look up the bookTO later
	private final int id;

	// Adds "transient" modifier to prevent serialization
	private transient BookTO bookTO;

	public LoadableBookModel(BookTO bookTO) {
		this(bookTO.getId());
		this.bookTO = bookTO;
	}

	public LoadableBookModel(int id) {
		if (id == 0) {
			throw new IllegalArgumentException();
		}
		this.id = id;
	}

	@Override
	protected Object load() {
		return getBookServicesManager().getBook(id);
	}

	private BookServicesManager getBookServicesManager() {
		return ((BookStoreApplication) Application.get()).getBookServicesManager();
	}


}
