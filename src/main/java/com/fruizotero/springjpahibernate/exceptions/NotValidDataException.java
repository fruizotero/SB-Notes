package com.fruizotero.springjpahibernate.exceptions;

import java.util.Map;

public class NotValidDataException extends RuntimeException {

    private Map<String, String> errors ;

    public NotValidDataException(String message, Map<String, String> errors) {
        super(message);
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
