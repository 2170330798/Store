package com.cy.store.controller.ex;

import org.apache.tomcat.util.http.fileupload.FileUploadException;

public class FileStateException extends FileUploadException {
    public FileStateException() {
        super();
    }

    public FileStateException(String msg) {
        super(msg);
    }

    public FileStateException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public FileStateException(Throwable cause) {
        super(cause);
    }
}
