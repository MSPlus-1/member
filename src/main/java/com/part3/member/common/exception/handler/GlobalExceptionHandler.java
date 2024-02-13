package com.part3.member.common.exception.handler;

import com.part3.member.common.dto.CommonResponse;
import com.part3.member.common.exception.CustomException;
import com.part3.member.common.exception.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.part3.member.common.dto.CommonResponse.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Application exception handle
     */
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<CommonResponse<ErrorResponse>> handleCustomException(
            final CustomException exception
    ) {
        final ErrorResponse errorResponse = new ErrorResponse(exception.getError());

        return new ResponseEntity<>(
                failed(errorResponse),
                exception.getHttpStatus()
        );
    }
}
