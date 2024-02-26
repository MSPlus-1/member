package com.part3.msplus.oauth2.application;

import com.part3.msplus.IntegrationTestSupport;
import com.part3.msplus.member.command.domain.entity.*;
import com.part3.msplus.member.command.domain.repository.MemberRepository;
import com.part3.msplus.member.command.domain.repository.MemberRoleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class IdTokenFacadeTest extends IntegrationTestSupport {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberRoleRepository memberRoleRepository;

    @Autowired
    private IdTokenFacade idTokenFacade;

    @Test
    @DisplayName("인증서버로 부터 받은 id token으로 member 사비스의 토큰을 만든다.")
    void makeApplicationIdToken() {
        // given
        String idToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiZW1haWwiOiJ0ZXN0MUBnbWFpbC5jb20iLCJpYXQiOjE3MDg5MjkzODEsImV4cCI6MTcwODkzMTE4MX0.AXILNLbDVwFnCVHBxqRIizFzienawkyEZnqtYil-oL0";
        AuthProvider authProvider = AuthProvider.GOOGLE;

        memberRepository.save(
                Member.builder()
                        .memberInfo(
                                MemberInfo.of("test1", "tester", "tester", "010-0000-0000")
                        )
                        .email(Email.from("test1@gmail.com"))
                        .password(Password.from("12345678"))
                        .memberRole(memberRoleRepository.findByRole(Role.ROLE_USER).get())
                        .build()
        );

        // when
        String token = idTokenFacade.makeApplicationIdTokenBy(idToken, authProvider);

        // then
        assertThat(token).isNotBlank();
    }
}