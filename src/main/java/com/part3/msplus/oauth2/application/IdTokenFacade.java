package com.part3.msplus.oauth2.application;

import com.part3.msplus.global.exception.dto.Error;
import com.part3.msplus.global.util.JwtUtils;
import com.part3.msplus.member.command.domain.entity.*;
import com.part3.msplus.member.command.domain.repository.MemberRepository;
import com.part3.msplus.member.command.domain.repository.MemberRoleRepository;
import com.part3.msplus.member.command.domain.service.CreateMemberService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class IdTokenFacade {

    private final MemberRepository memberRepository;
    private final MemberRoleRepository memberRoleRepository;
    private final CreateMemberService createMemberService;

    /**
     * Make client application id_token
     * @param authProviderIdToken id_token from authentication server
     * @param authProvider        authentication server
     * @return                    id_token (jwt)
     */
    @Transactional
    public String makeApplicationIdTokenBy(String authProviderIdToken, AuthProvider authProvider) {
        log.info(authProviderIdToken);

        Map<String, Object> payload = JwtUtils.getPayloadMap(authProviderIdToken);

        String email = (String) payload.get("email");
        validateEmailFromToken(email);

        Long memberId = memberRepository.findByEmail(Email.from(email))
                .map(Member::getId)
                .orElseGet(() -> createMemberService.createMember(
                        Member.builder()
                                .email(Email.from(email))
                                .memberRole(memberRoleRepository.findByRole(Role.ROLE_USER).orElse(MemberRole.from(Role.ROLE_GUEST)))
                                .authProvider(authProvider)
                                .build()
                ).getId());

        return JwtUtils.createJwt(String.valueOf(memberId), email);
    }

    private void validateEmailFromToken(String email) {
        if (!StringUtils.hasText(email)) {
            throw new IllegalArgumentException(String.valueOf(Error.NOT_FOUND_EMAIL_FROM_TOKEN));
        }
    }
}
