package com.part3.msplus.common.exception;

import com.part3.msplus.common.exception.dto.Error;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {

    private final Error error;
    private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public CustomException(Error error) {
        super(error.getMessage());
        this.error = error;
    }

    public CustomException(Error error, HttpStatus httpStatus) {
        this(error);
        this.httpStatus = httpStatus;
    }
}
