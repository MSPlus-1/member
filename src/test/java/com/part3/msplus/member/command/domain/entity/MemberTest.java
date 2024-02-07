package com.part3.msplus.member.command.domain.entity;

import com.part3.msplus.common.exception.CustomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("사용자 엔티티 테스트")
class MemberTest {
    
    @Test
    @DisplayName("사용자 엔티티를 생성한다.")
    void createMember() {
        // given
        Member member = Member.builder()
                .email(Email.from("test@mailplug.co.kr"))
                .password(Password.from("12345678"))
                .memberInfo(MemberInfo.of(
                        "test123",
                        "tester",
                        "tester_nick",
                        null
                ))
                .memberRole(MemberRole.from(Role.ROLE_USER))
                .build();

        // when
        // then
        assertDoesNotThrow(() -> {
            member.getPassword().validationMatchPassword("12345678");
        });
    }

    @Test
    @DisplayName("이메일 정책에 위배되어 사용자 엔티티를 생성하지 못한다.")
    void denyCreateMember_emailInvalid() {
        // given
        // when
        // then
        assertThrows(CustomException.class, () -> {
            Email.from("ttt_mailplug.co.kr");
        });
    }
}
