package com.cy.store.service.ex;


//更新数据时产生的未知异常
public class UpdateException extends  ServiceException{

    protected UpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public UpdateException(Throwable cause) {
        super(cause);
    }

    public UpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateException(String message) {
        super(message);
    }

    public UpdateException() {
        super();
    }
}
