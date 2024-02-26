package com.part3.msplus.oauth2.command.domain;

public interface Oauth2Client {
    String getAuthorizationUri(Oauth2Property oauth2Property);

    String getToken(String code, String state, Oauth2Property oauth2Property);
}
