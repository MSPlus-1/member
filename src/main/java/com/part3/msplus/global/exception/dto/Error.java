package com.part3.msplus.global.exception.dto;

import lombok.Getter;

@Getter
public enum Error {

    // common : 10,000
    INVALID_INPUT_VALUE(10000, "Invalid input value"),
    INVALID_PASSWORD_ALGORITHM(10001, "Invalid password algorithm"),
    INVALID_EMAIL_FORMAT(10002, "Invalid email format"),
    INVALID_PASSWORD_FORMAT(10003, "Invalid password format"),
    INVALID_AUTH_PROVIDER(10004, "Invalid auth provider(service)"),
    NOT_FOUND_EMAIL_FROM_TOKEN(10005, "Not found email from provider's id token"),

    // auth : 20,000
    UNAUTHORIZED(20000, "Unauthenticated Access"),
    MISMATCH_PASSWORD(20001, "Mismatch password"),
    INVALID_STATE_VALUE(20002, "Invalid state value"),

    // server : 30,000
    INTERNAL_SERVER_ERROR(30000, "Internal server error"),
    ;

    private final int code;
    private final String message;

    Error(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
