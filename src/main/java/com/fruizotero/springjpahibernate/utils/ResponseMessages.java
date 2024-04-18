package com.fruizotero.springjpahibernate.utils;

public enum ResponseMessages {

    GET_ROLES("Los roles se solicitaron de manera éxitosa"),
    NOT_FOUND_ROLE("Rol no encontrado");


    private String message;

    ResponseMessages(String message) {

        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
