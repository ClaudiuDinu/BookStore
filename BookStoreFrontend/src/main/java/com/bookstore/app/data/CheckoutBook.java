package com.bookstore.app.data;

import java.io.Serializable;

public class CheckoutBook implements Serializable {
	private BookData book;

	// Set book quantity to 1 by default
	private int quantity = 1;
	
	private boolean markedForRemoval;

	public CheckoutBook(BookData book) {
		this.book = book;
	}

	public BookData getBookData() {
		return book;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	/* Returns the price depending upon the quantity entered */
	public float getTotalPrice() {
		return getBookData().getPrice() * getQuantity();
	}

	/*
	 * This class is just an extension of the book object. Hence delegate the
	 * following method implementation to the original book object.
	 */
	public boolean equals(Object obj) {
		return book.equals(obj);
	}

	public int hashCode() {
		return book.hashCode();
	}

	

	public boolean isMarkedForRemoval() {
		return markedForRemoval;
	}

	public void setMarkedForRemoval(boolean markedForRemoval) {
		this.markedForRemoval = markedForRemoval;
	}
}
