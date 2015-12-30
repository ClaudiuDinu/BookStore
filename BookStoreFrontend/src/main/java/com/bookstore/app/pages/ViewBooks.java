package com.bookstore.app.pages;

import com.bookstore.app.application.BookStoreApplication;
import com.bookstore.app.commons.bo.BookCategoryTO;
import com.bookstore.app.data.BookData;
import com.bookstore.app.data.provider.BookDataProvider;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.OddEvenItem;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ViewBooks extends WebPage {

    private List booksMarkedForCheckout = new ArrayList();

    public ViewBooks() {
        final Form form = new Form("bookForm");
        final BookDataProvider dataProvider = new BookDataProvider();
        final DataView bookDataView = new BookDataView("books", dataProvider);
        /*
         * As the method call indicates, this will ensure that only two items
		 * are displayed per page.
		 */
        bookDataView.setItemsPerPage(3);
        /*
         * But a navigator needs to be associated with the DataView to achieve
		 * paging.
		 */
        form.add(new PagingNavigator("navigator", bookDataView));

        List<BookCategoryTO> categoryTOs = getBookCategories();
        categoryTOs.add(0,new BookCategoryTO(0,"All"));
        DropDownChoice categories = new CategoryDropDownChoice("categories",
                new PropertyModel(dataProvider, "category"),
                categoryTOs, new ChoiceRenderer("name", "id"), bookDataView);
        // The drop-down should show a valid value selected.
        categories.setNullValid(false);

        form.add(categories);
        form.add(bookDataView);
        form.add(new Button("addToCart") {
            public void onSubmit() {
                //Set the response as the Checkout page passing in the books selected by the user.
                setResponsePage(new Checkout(ViewBooks.this.booksMarkedForCheckout));
            }
        });

        add(form);
    }



    // DataView class for tabular data display.
    // It works similarly to the ListView component discussed in
    // Chapter 2.
    class BookDataView extends DataView {

        public BookDataView(String id, IDataProvider dataProvider) {
            super(id, dataProvider);
        }

        @Override
        protected void populateItem(Item item) {
            BookData book = (BookData) item.getModelObject();
            item.add(new Label("title", book.getTitle()));
            item.add(new Label("author", book.getAuthor()));
            item.add(new Label("publisher", book.getPublisher()));
            item.add(new Label("price", book.getPrice()));
            item.add(new MyCheckBox("selected", new CheckBoxModel(book.getId())));

        }

        @Override
        protected Item newItem(final String id, int index, final IModel model) {
            return new OddEvenItem(id, index, model);
        }

        // A custom CheckBox that will result in Form submit
        // when checked/unchecked
        class MyCheckBox extends CheckBox {
            public MyCheckBox(String id) {
                this(id, Model.of(""));
            }

            public MyCheckBox(String id, IModel model) {
                super(id, model);
            }

            protected boolean wantOnSelectionChangedNotifications() {
                return true;
            }

        }

    }

    // A DropDownChoice that represents the displayed categories
    class CategoryDropDownChoice extends DropDownChoice {

        DataView bookDataView;

        public CategoryDropDownChoice(String id, IModel model,
                                      List displayData, IChoiceRenderer renderer, DataView bookDataView) {
            super(id, model, displayData, renderer);
            this.bookDataView = bookDataView;
        }

        // Indicate that you want a server-side notification
        // when the user changes the drop-down selection.
        public boolean wantOnSelectionChangedNotifications() {
            return true;
        }

        public void onSelectionChanged(Object newSelection) {
			/*
			 * Note that you are not required to explicitly update the category -
			 * dataProvider.setCategory(newSelection.toString());
			 *
			 * BookDataProvider's category field is set as the model for
			 * DropdownChoice and hence will be automatically updated when the
			 * form submits. But the DataView model that displays the books
			 * belonging to a particular category needs to reset its current
			 * page. You do that through the following method call.
			 */
            bookDataView.setCurrentPage(0);
            // When selection changes, update the Form component model.
            getForm().process(null);
        }
    }

    private class CheckBoxModel implements IModel, Serializable {
        // Book ID the model represents
        private final Integer bookId;

        public CheckBoxModel(int bookId) {
            this.bookId = new Integer(bookId);
        }

        public IModel getNestedModel() {
            return null;
        }

        /*
         * Wicket calls this method when rendering the check box. CheckBox needs
         * to show up selected if the corresponding book has already been
         * selected.
         */
        public Object getObject() {
            return isBookAlreadyMarkedForCheckout();
        }

        private Boolean isBookAlreadyMarkedForCheckout() {
            if (booksMarkedForCheckout.contains(bookId))
                return Boolean.TRUE;
            else
                return Boolean.FALSE;
        }

        /*
         * Wicket calls this method when pushing the user selection back to the
         * model. If the user has selected a book, the method adds it to the
         * back-end store after making sure that it has not been selected
         * before. If the user has unchecked the check box, the method removes
         * it from the back-end store if present.
         */
        public void setObject(Object object) {
            boolean selected = (Boolean) object;
            boolean previouslySelected = isBookAlreadyMarkedForCheckout();
            if (selected) {
                if (!previouslySelected) {
                    booksMarkedForCheckout.add(bookId);
                }
            } else {
                if (previouslySelected) {
                    booksMarkedForCheckout.remove(bookId);
                }
            }
        }

        public void detach() {
        }
    }


    public List getBookCategories() {
        BookStoreApplication application = (BookStoreApplication) getApplication();
        return application.getBookServicesManager().getBookCategories();
    }

}
