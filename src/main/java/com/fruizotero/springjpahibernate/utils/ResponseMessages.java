package com.fruizotero.springjpahibernate.utils;

public enum ResponseMessages {

    GET_ROLES("Los roles se solicitaron con éxito"),
    GET_ROLE("El rol se solicitó con éxito"),
    CREATE_ROLE("El rol se creó con éxito"),
    UPDATE_ROLE("El rol se actualizó con éxito"),
    DELETE_ROLE("El rol se eliminó con éxto"),
    NOT_FOUND_ROLE("El rol no existe"),
    CREATE_ROLE_ERROR("No se pudo crear el rol"),
    UPDATE_ROLE_ERROR("No se pudo actualizar el rol"),
    DELETE_ROLE_ERROR("No se pudo eliminar el rol"),
    GET_USERS("Los usuarios se solicitaron con éxito"),
    GET_USER("El usuario no existe"),
    CREATE_USER("El usuario se creó con éxito"),
    CREATE_USER_ERROR("No se pudo crear el usuario"),
    UPDATE_USER("El usuario se actualizó con éxto"),
    UPDATE_USER_ERROR("No se pudo actualizar el usuario "),
    DELETE_USER("El usuario se elimino con éxito"),
    INVALID_EMAIL("Email no válido"),
    INVALID_ROL("Rol no válido"),
    NOT_FOUND_USER("Usuario no encontrado"),
    ;


    private String message;

    ResponseMessages(String message) {

        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
