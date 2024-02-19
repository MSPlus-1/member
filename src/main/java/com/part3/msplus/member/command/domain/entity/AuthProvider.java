package com.part3.msplus.member.command.domain.entity;

import com.part3.msplus.global.exception.dto.Error;

import java.util.Arrays;

public enum AuthProvider {
    KAKAO("kakao"),
    GOOGLE("google"),
    LOCAL("local"),
    ;

    private final String authService;

    AuthProvider(String authService) {
        this.authService = authService;
    }

    public static AuthProvider findByAuthService(String authService) {
        return Arrays.stream(AuthProvider.values())
                .filter(authProvider -> authProvider.authService.equals(authService))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.valueOf(Error.INVALID_AUTH_PROVIDER)));
    }
}
