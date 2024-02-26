package com.part3.msplus.oauth2.infra;

import com.part3.msplus.global.exception.CustomException;
import com.part3.msplus.global.exception.dto.Error;
import org.springframework.http.HttpStatus;

public class InvalidStateException extends CustomException {

    public InvalidStateException(Error error) {
        super(error);
    }
}
