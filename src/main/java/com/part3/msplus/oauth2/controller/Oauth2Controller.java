package com.part3.msplus.oauth2.controller;

import com.part3.msplus.global.dto.CommonResponse;
import com.part3.msplus.member.command.domain.entity.AuthProvider;
import com.part3.msplus.oauth2.application.IdTokenFacade;
import com.part3.msplus.oauth2.application.Oauth2Service;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@RequestMapping("/api/v1/oauth2")
@RestController
public class Oauth2Controller {

    private final Oauth2Service oauth2Service;

    private final IdTokenFacade idTokenFacade;

    /**
     * REST API - Get authorizationCode from authentication server (service)
     * Redirect to authentication server
     * @param service  authentication server
     */
    @GetMapping("/authorize/{service}")
    public void authorize(@PathVariable String service, HttpServletResponse response) throws IOException {
        AuthProvider authProvider = AuthProvider.findByAuthService(service);
        String authorizeUri = oauth2Service.getAuthorizationUri(authProvider);

        response.sendRedirect(authorizeUri);
    }

    /**
     * Response JWT
     * @param service authentication server
     * @param code    authorization code
     * @param state   authentication value used to prevent CSRF
     * @return        id_token (jwt)
     */
    @GetMapping("/callback/{service}")
    public ResponseEntity<CommonResponse<String>> callback(
            @PathVariable String service,
            @RequestParam String code,
            @RequestParam String state
    ) {
        AuthProvider authProvider = AuthProvider.findByAuthService(service);
        String authProviderIdToken = oauth2Service.getAuthProviderIdToken(authProvider, code, state);
        //TODO : refresh token
        String idToken = idTokenFacade.makeApplicationIdTokenBy(authProviderIdToken, authProvider);

        return ResponseEntity.ok(
                CommonResponse.succeed(idToken)
        );
    }
}
