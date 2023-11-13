package com.cy.store.service.ex;

public class AddressCountLimitException extends ServiceException{
    protected AddressCountLimitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public AddressCountLimitException(Throwable cause) {
        super(cause);
    }

    public AddressCountLimitException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressCountLimitException(String message) {
        super(message);
    }

    public AddressCountLimitException() {
        super();
    }
}
