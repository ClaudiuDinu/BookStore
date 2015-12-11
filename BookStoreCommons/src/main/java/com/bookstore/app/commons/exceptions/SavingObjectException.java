package com.bookstore.app.commons.exceptions;

/**
 * Created by cdinu on 12/7/2015.
 */
public class SavingObjectException extends Exception{
    /**
     * Create a new instance of <code>SavingObjectException</code> holding the
     * message.
     *
     * @param message
     *            - the message that will the shown when the Exception will be
     *            thrown
     */
    public SavingObjectException(String message) {
        super(message);
    }

    /**
     * Create a new instance of <code>SavingObjectException</code> holding the
     * original exception.
     *
     * @param exception
     *            - The current exception that has been thrown
     */
    public SavingObjectException(Exception exception) {
        super(exception);
    }

    /**
     * Create a new instance of <code>SavingObjectException</code> holding the
     * message and also the original exception.
     *
     * @param message
     *            - the message that will the shown when the Exception will be
     *            thrown
     *
     * @param exception
     *            - the current exception that has been thrown
     */
    public SavingObjectException(String message, Exception exception) {
        super(message, exception);
    }
}
