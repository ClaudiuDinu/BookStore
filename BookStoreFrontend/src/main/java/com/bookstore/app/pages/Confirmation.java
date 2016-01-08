package com.bookstore.app.pages;


import com.bookstore.app.data.Cart;
import com.bookstore.app.data.CheckoutBook;
import com.bookstore.app.pages.layout.SecuredBookstoreTamplate;
import com.bookstore.app.session.BookStoreSession;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;

public class Confirmation extends SecuredBookstoreTamplate {
	public Confirmation() {

		add(new ListView<CheckoutBook>("booksBought", getCart().getCheckoutBooks()) {
			@Override
			protected void populateItem(ListItem<CheckoutBook> item) {
				CheckoutBook book = (CheckoutBook) item.getModelObject();
				item.setModel(new CompoundPropertyModel<CheckoutBook>(book));
				item.add(new Label("book.title"));
				item.add(new Label("quantity"));
				item.add(new Label("totalPrice"));
			}
		});

		add(new Label("totalPrice", new PropertyModel<Object>(getCart(), "totalPrice")));
	}

	private Cart getCart() {
		return ((BookStoreSession) getSession()).getCart();
	}
}
