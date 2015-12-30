package com.bookstore.app.pages;



import com.bookstore.app.application.BookStoreApplication;
import com.bookstore.app.data.BookData;
import com.bookstore.app.data.Cart;
import com.bookstore.app.data.CheckoutBook;
import com.bookstore.app.manager.BookServicesManager;
import com.bookstore.app.session.BookStoreSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.OddEvenItem;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Checkout extends WebPage {
	private Cart cart;

	public Cart getCart() {
		return cart;
	}

	// You might get to this page from another link. So you need a
	// default constructor as well.
	public Checkout() {
		this(Collections.EMPTY_LIST);
	}

	public Checkout(List checkoutBooksIds) {		
		cart = ((BookStoreSession) getSession()).getCart();
		addBooksToCart(checkoutBooksIds);		
		Form checkoutForm = new Form("checkoutForm");

		final DataView<CheckoutBook> books = new DataView("checkoutBooks",
				new ListDataProvider<CheckoutBook>(cart.getCheckoutBooks())) {
			protected void populateItem(final Item item) {
				CheckoutBook cBook = (CheckoutBook) item.getModelObject();
				final CompoundPropertyModel model = new CompoundPropertyModel<CheckoutBook>(
						cBook);
				// Model is set at parent level, and child components will look
				// it up.
				item.setModel(model);
				// Evaluates model to cBook.getBook().getTitle()
				item.add(new Label("book.title"));
				// Evaluates model to cBook.getBook().getAuthor()
				item.add(new Label("book.author"));
				// Evaluates model to cBook.getBook().getPrice()
				item.add(new Label("book.price"));
				// Evaluates to cBook.getQuantity() & cBook.setQuantity()
				item.add(new TextField("quantity"));
				/* CheckoutBook is the model. */
				item.add(new CheckBox("markedForRemoval"));
			}

			@Override
			protected Item newItem(final String id, int index, final IModel model) {
				return new OddEvenItem(id, index, model);
			}

		};
		checkoutForm.add(books);
		// Get the cart to determine the total price.
		checkoutForm.add(new Label("priceTotal", new PropertyModel<Object>(this.cart,
				"totalPrice")));
		/*
		 * The book quantity is tied to the CheckoutBook that is present in the
		 * cart. The "total price" is also tied to the cart through the use of
		 * the PropertyModel class. Hence the new price calculation is
		 * automatically taken care of. So "recalculate" comes for free!
		 */
		checkoutForm.add(new Button("recalculate") {
			public void onSubmit() {
			}
		});
		
				
		Button checkOutButton = new Button("checkOut"){		
		
			public void onSubmit() {
				//setRedirect(true);

				setResponsePage(new Confirmation());
			}
			
			//The checkout button should be enabled only
			//when the user is already logged in
			//Override Component.isEnabled()
			public boolean isEnabled(){
				return ((BookStoreSession) getSession()).isUserLoggedIn();
			}
		};
		
		checkoutForm.add(checkOutButton);
		checkOutButton.setDefaultFormProcessing(false);
		checkoutForm.add(new Button("removeBooks") {
			// When asked to remove the books, remove them from the cart.
			public void onSubmit() {
				Cart cart = ((BookStoreSession) getSession()).getCart();
				for (Iterator iter = cart.getCheckoutBooks().iterator(); iter
						.hasNext();) {
					CheckoutBook book = (CheckoutBook) iter.next();
					if (book.isMarkedForRemoval()) {
						iter.remove();
					}
				}
			}
		});
		checkoutForm.add(new Link("login"){

			@Override
			public void onClick() {

				//setResponsePage(new Login(Checkout.this));
			}			
			//The Login Link should be visible only
			//when the user is already NOT logged in
			//Override Component.isVisible()
			@Override
			public boolean isVisible(){
				return !((BookStoreSession) getSession()).isUserLoggedIn();
			}
		
		});
		add(checkoutForm);
	}

	private void addBooksToCart(List booksMarkedForCheckout) {
		BookServicesManager bookServicesManager = ((BookStoreApplication) getApplication())
				.getBookServicesManager();
		Cart cart = getCart();
		for (Iterator iter = booksMarkedForCheckout.iterator(); iter.hasNext();) {
			int bookId = ((Integer) iter.next()).intValue();
			if (!cart.containsBook(bookId)) {
				BookData book = bookServicesManager.getBook(bookId);
				cart.addToCart(new CheckoutBook(book));
			}
		}
	}
	
	
}
