package com.part3.msplus.common.exception.dto;

import lombok.Getter;

@Getter
public class ErrorResponse {

    /**
     * custom application error code
     */
    private final int code;

    /**
     * custom application error message
     */
    private final String message;

    public ErrorResponse(Error error) {
        this.code = error.getCode();
        this.message = error.getMessage();
    }
}
