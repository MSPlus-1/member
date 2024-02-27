package com.part3.msplus.oauth2.infra;

import com.part3.msplus.IntegrationTestSupport;
import com.part3.msplus.member.command.domain.entity.AuthProvider;
import com.part3.msplus.oauth2.command.domain.Oauth2Property;
import com.part3.msplus.oauth2.command.domain.Oauth2PropertyFactory;
import com.part3.msplus.oauth2.command.domain.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class idTokenClientTest extends IntegrationTestSupport {

    @Autowired
    private idTokenClient idTokenClient;

    @Autowired
    private Oauth2PropertyFactory oauth2PropertyFactory;

    @Test
    @DisplayName("property로 구글 인증 서비스에 대한 로그인 페이지 url을 얻어올 수 있다")
    void getAuthorizeUrl() {
        // given
        Oauth2Property oauth2Property = oauth2PropertyFactory.getOauth2Property(AuthProvider.GOOGLE);
        State state = State.generate();
        // when
        String authorizationUri = idTokenClient.getAuthorizationUri(oauth2Property, state);

        // then
        System.out.println("authorizationUri = " + authorizationUri);

        assertThat(authorizationUri).isEqualTo(
                "https://accounts.google.com/o/oauth2/v2/auth?" +
                        "client_id=clientId&response_type=code&redirect_uri=" +
                        "http://localhost:8080/api/v1/oauth2/callback/google&" +
                        "scope=email%20profile&state=" + state.getId().toString()
        );
    }
}