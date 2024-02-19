package com.part3.msplus.oauth2.command.domain;

import com.part3.msplus.member.command.domain.entity.AuthProvider;
import com.part3.msplus.oauth2.infra.Oauth2GoogleProperty;
import com.part3.msplus.oauth2.infra.Oauth2KakaoProperty;
import com.part3.msplus.oauth2.infra.Oauth2LocalProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class Oauth2PropertyFactory {

    private final Oauth2KakaoProperty oauth2KakaoProperty;
    private final Oauth2GoogleProperty oauth2GoogleProperty;
    private final Oauth2LocalProperty oauth2LocalProperty;

    /**
     * Get oauth2 property
     * @param authProvider authentication server
     * @return             oauth2 property
     */
    public Oauth2Property getOauth2Property(AuthProvider authProvider) {

        return switch (authProvider) {
            case KAKAO -> oauth2KakaoProperty;
            case GOOGLE -> oauth2GoogleProperty;
            case LOCAL -> oauth2LocalProperty;
        };
    }
}