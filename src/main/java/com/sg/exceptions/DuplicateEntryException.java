package com.sg.exceptions;

public class DuplicateEntryException extends ServiceException {

    public DuplicateEntryException() {
    }

    public DuplicateEntryException(String message) {
        super(message);
    }
}
