package com.fruizotero.springjpahibernate.utils;

public enum ResponseMessages {

    GET_ROLES("Los roles se solicitaron de manera éxitosa"),
    GET_ROLE("El rol se solicitó de manera éxitosa"),
    CREATE_ROLE("El rol se creó de manera éxitosa"),
    UPDATE_ROLE("El rol se actualizó correctamente"),
    DELETE_ROLE("El rol se eliminó correctamente"),
    NOT_FOUND_ROLE("Rol no encontrado"),
    CREATE_ROLE_ERROR("No se pudo crear el rol"),
    UPDATE_ROLE_ERROR("No se pudo modificar el rol"),
    DELETE_ROLE_ERROR("No se pudo eliminar el rol"),
    ;


    private String message;

    ResponseMessages(String message) {

        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
