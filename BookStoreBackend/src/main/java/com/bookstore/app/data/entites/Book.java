package com.bookstore.app.data.entites;

import com.bookstore.app.commons.bo.BookTO;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Book")
public class Book implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID", updatable = false)
	private int id;

	@Basic
	@Column(name="TITLE", nullable=false,updatable=false)
	private String title;

	@Basic
	@Column(name="AUTHOR", nullable=false,updatable=false)
	private String author;

	@Basic
	@Column(name="PRICE", nullable=false,updatable=true)
	private float price;

	@Basic
	@Column(name="PUBLISHER", nullable=false,updatable=false)
	private String publisher;


	@ManyToOne
	@JoinColumn(name = "BOOK_CATEGORY_ID")
	private BookCategory bookCategory;

	public Book(){ }

	public Book(int id, String author, BookCategory bookCategory, String title, float price,
				String publisher) {
		super();
		this.id = id;
		this.author = author;
		this.bookCategory = bookCategory;
		this.title = title;
		this.price = price;
		this.publisher = publisher;
	}

	// Define Java bean style getters for all the properties.

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public BookCategory getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(BookCategory category) {
		this.bookCategory = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BookTO asTO(){
		return new BookTO(getId(), getAuthor(),getBookCategory().asTO(),getPrice(),getPublisher(),getTitle());
	}
}
