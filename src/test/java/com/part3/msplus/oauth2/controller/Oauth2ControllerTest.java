package com.part3.msplus.oauth2.controller;

import com.part3.msplus.member.command.domain.entity.AuthProvider;
import com.part3.msplus.ControllerTestSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class Oauth2ControllerTest extends ControllerTestSupport {

    @Test
    @DisplayName("member 토큰을 발급한다.")
    void getToken() throws Exception {
        // given
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiZW1haWwiOiJ0ZXN0MUBnbWFpbC5jb20iLCJpYXQiOjE3MDg5MjkzODEsImV4cCI6MTcwODkzMTE4MX0.AXILNLbDVwFnCVHBxqRIizFzienawkyEZnqtYil-oL0";
        when(oauth2Service.getAuthProviderIdToken(any(), anyString(), anyString()))
                .thenReturn(jwt);
        when(idTokenFacade.makeApplicationIdTokenBy(jwt, AuthProvider.GOOGLE))
                .thenReturn(jwt);

        // when
        // then
        mockMvc.perform(get("/api/v1/oauth2/callback/google")
                .param("code", "code")
                .param("state", "state")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value(jwt))
                .andExpect(jsonPath("$.error").doesNotExist())
                .andDo(print());
    }
}