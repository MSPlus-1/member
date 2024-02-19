package com.part3.msplus.oauth2.command.domain;

public interface Oauth2Property {

    String getClientId();

    String getSecretId();

    String getRedirectUri();

    String getResponseType();

    String getGrantType();

    String getScope();

    String getAccessTokenUri();

    String getAuthorizationUri();
}
