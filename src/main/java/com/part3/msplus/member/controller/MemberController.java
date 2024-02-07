package com.part3.msplus.member.controller;

import com.part3.msplus.member.command.domain.entity.Member;
import com.part3.msplus.member.command.domain.service.CreateMemberService;
import com.part3.msplus.member.controller.dto.request.CreateMemberDTO;
import com.part3.msplus.member.query.ReadMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final ReadMemberService memberReadService;
    private final CreateMemberService createMemberService;

    @PostMapping("/members")
    public Member createMember(@RequestBody CreateMemberDTO createMemberDTO) {
        Member member = Member.builder()
                .name(createMemberDTO.getName())
                .email(createMemberDTO.getEmail())
                .password(createMemberDTO.getPassword())
                .phone(createMemberDTO.getPhone())
                .nickname(createMemberDTO.getNickname())
                .memberId(createMemberDTO.getMemberId())
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
