package com.part3.msplus.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.part3.msplus.common.exception.dto.ErrorResponse;
import lombok.Getter;

@Getter
public class CommonResponse<T> {

    /**
     * response body data
     */
    private final T data;

    @JsonProperty(value = "error")
    private final ErrorResponse errorResponse;

    private CommonResponse(T data, ErrorResponse errorResponse) {
        this.data = data;
        this.errorResponse = errorResponse;
    }

    /**
     * 성공 응답
     * @param data response body data
     */
    public static <T> CommonResponse<T> succeed(T data) {
        return new CommonResponse<>(data, null);
    }

    /**
     * 실패 응답
     */
    public static <T> CommonResponse<T> failed(ErrorResponse errorResponse) {
        return new CommonResponse<>(null, errorResponse);
    }
}
