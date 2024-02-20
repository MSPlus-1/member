package com.part3.msplus.global.exception.dto;

import lombok.Builder;
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

    @Builder
    public ErrorResponse(Error error) {
        this.code = error.getCode();
        this.message = error.getMessage();
    }
}
