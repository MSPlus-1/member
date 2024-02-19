package com.part3.msplus.oauth2.infra;

import com.part3.msplus.oauth2.command.domain.Oauth2Property;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties("oauth2.config.kakao")
public class Oauth2KakaoProperty implements Oauth2Property {

    private final String clientId;

    private final String secretId;

    private final String redirectUri;

    private final String responseType;

    private final String grantType;

    private final String scope;

    private final String accessTokenUri;

    private final String authorizationUri;
}
