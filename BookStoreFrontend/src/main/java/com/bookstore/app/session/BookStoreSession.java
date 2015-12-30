package com.bookstore.app.session;


import com.bookstore.app.commons.bo.UserTO;
import com.bookstore.app.data.Cart;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;


public class BookStoreSession extends WebSession {
    private Cart cart;

    private UserTO user;

    public BookStoreSession(Request request) {
        super(request);

    }

    /*
     * Some users might not be interesting in buying a book. Maybe they are
     * interested in reading a book review, for example. So create the cart on
     * demand and not by default.
     */
    public Cart getCart() {
        if (cart == null)
            cart = new Cart();
        return cart;
    }

    public UserTO getUser() {
        return user;
    }

    public void setUser(UserTO user) {
        this.user = user;
    }

    // A helper to determine whether the user is logged in
    public boolean isUserLoggedIn() {
        return (user != null);
    }
}
