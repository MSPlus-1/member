package com.part3.msplus.member.command.domain.repository;

import com.part3.msplus.member.command.domain.entity.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("사용자 레포지토리 테스트")
@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberRoleRepository memberRoleRepository;

    @Test
    @DisplayName("사용자 엔티티를 생성하고 디비에 저장한다.")
    void persistMember() {
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
                .memberRole(memberRoleRepository.findByRole(Role.ROLE_USER).get())
                .build();

        // when
        Member savedMember = memberRepository.save(member);

        // then
        assertThat(savedMember.getMemberRole().getRole()).isEqualTo(Role.ROLE_USER);
    }
}
