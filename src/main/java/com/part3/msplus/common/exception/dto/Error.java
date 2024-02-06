package com.part3.msplus.common.exception.dto;

import lombok.Getter;

@Getter
public enum Error {

    // common : 10,000
    INVALID_INPUT_VALUE(10000, "Invalid input value"),

    // auth : 20,000
    UNAUTHORIZED(20000, "Unauthenticated Access"),
    ;

    private final int code;
    private final String message;

    Error(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
