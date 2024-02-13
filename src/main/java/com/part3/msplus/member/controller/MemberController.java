package com.part3.msplus.member.controller;

import com.part3.msplus.common.exception.CustomException;
import com.part3.msplus.common.exception.dto.Error;
import com.part3.msplus.member.command.domain.entity.*;
import com.part3.msplus.member.command.domain.repository.MemberRoleRepository;
import com.part3.msplus.member.command.domain.service.CreateMemberService;
import com.part3.msplus.member.controller.dto.request.CreateMemberDTO;
import com.part3.msplus.member.query.ReadMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MemberController {

    @Autowired
    private final ReadMemberService memberReadService;
    @Autowired
    private final CreateMemberService createMemberService;
    @Autowired
    private final MemberRoleRepository memberRoleRepository;

    @PostMapping("/members")
    public Member createMember(@RequestBody CreateMemberDTO createMemberDTO) {

        MemberRole memberRole = memberRoleRepository.findByRole(Role.ROLE_USER).orElseThrow(() -> new CustomException(Error.INVALID_INPUT_VALUE));
        Member member = Member.builder()
                .email(Email.from(createMemberDTO.email()))
                .password(Password.from(createMemberDTO.password()))
                .memberInfo(MemberInfo.of(
                        createMemberDTO.memberId(),
                        createMemberDTO.name(),
                        createMemberDTO.nickname(),
                        createMemberDTO.phone()
                ))
                .memberRole(memberRole)
                .build();
        return createMemberService.createMember(member);
    }

    @GetMapping("/members")
    public Map<String, String> getMembers() {
        return Map.of("message", "Hello, World!");
    }

    @GetMapping("/members/{id}")
    public Optional<Member> getMember(@PathVariable Long id) {
        return memberReadService.findById(id);
    }
}
