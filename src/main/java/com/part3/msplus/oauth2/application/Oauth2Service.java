package com.part3.msplus.oauth2.application;

import com.part3.msplus.member.command.domain.entity.AuthProvider;
import com.part3.msplus.oauth2.command.domain.Oauth2Client;
import com.part3.msplus.oauth2.command.domain.Oauth2Property;
import com.part3.msplus.oauth2.command.domain.Oauth2PropertyFactory;
import com.part3.msplus.oauth2.command.domain.State;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class Oauth2Service {

    private final Oauth2PropertyFactory oauth2PropertyFactory;

    private final Oauth2Client oauth2Client;

    /**
     * Get authorization uri from authentication server
     * @param authProvider authentication server
     * @return             authorization uri
     */
    public String getAuthorizationUri(AuthProvider authProvider) {
        Oauth2Property oauth2Property = getOauth2Property(authProvider);
        State state = State.generate();

        return oauth2Client.getAuthorizationUri(oauth2Property, state);
    }

    /**
     * Get id_token from authentication server
     * @param authProvider authentication server
     * @param code         authorization code
     * @param state        authentication value used to prevent CSRF
     * @return             id_token (jwt)
     */
    public String getAuthProviderIdToken(AuthProvider authProvider, String code, String state) {
        Oauth2Property oauth2Property = getOauth2Property(authProvider);

        return oauth2Client.getToken(code, state, oauth2Property);
    }

    private Oauth2Property getOauth2Property(AuthProvider authProvider) {
        return oauth2PropertyFactory.getOauth2Property(authProvider);
    }
}