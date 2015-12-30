package com.bookstore.app.data;

import com.bookstore.app.commons.bo.BookTO;

/**
 * Created by cdinu on 12/11/2015.
 */
public class BookData extends BookTO {
    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
