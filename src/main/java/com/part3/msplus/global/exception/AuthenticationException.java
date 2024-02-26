package com.part3.msplus.global.exception;

import com.part3.msplus.global.exception.dto.Error;
import org.springframework.http.HttpStatus;

/**
 * 인증 실패 exception
 */
public class AuthenticationException extends CustomException {

    public AuthenticationException() {
        super(Error.UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
    }
}
