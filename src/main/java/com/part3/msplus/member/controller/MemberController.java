package com.part3.msplus.member.controller;

import com.part3.msplus.global.dto.CommonResponse;
import com.part3.msplus.member.command.domain.entity.*;
import com.part3.msplus.global.exception.CustomException;
import com.part3.msplus.global.exception.dto.Error;
import com.part3.msplus.member.command.domain.repository.MemberRoleRepository;
import com.part3.msplus.member.command.domain.service.CreateMemberService;
import com.part3.msplus.member.command.domain.service.DeleteMemberService;
import com.part3.msplus.member.command.domain.service.UpdateMemberService;
import com.part3.msplus.member.controller.dto.request.CreateMemberDTO;
import com.part3.msplus.member.controller.dto.request.UpdateMemberDTO;
import com.part3.msplus.member.query.ReadMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MemberController {

    @Autowired
    private final ReadMemberService readMemberService;
    @Autowired
    private final CreateMemberService createMemberService;
    @Autowired
    private final UpdateMemberService updateMemberService;
    @Autowired
    private final DeleteMemberService deleteMemberService;
    @Autowired
    private final MemberRoleRepository memberRoleRepository;

    @PostMapping("/members")
    public ResponseEntity<CommonResponse<Member>> createMember(@RequestBody CreateMemberDTO createMemberDTO) {

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

        return new ResponseEntity<>(
                CommonResponse.succeed(createMemberService.createMember(member)),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/members")
    public List<Member> getMembers() {
        return readMemberService.findAll();
    }

    @GetMapping("/members/{id}")
    public Optional<Member> getMember(@PathVariable Long id) {
        return readMemberService.findById(id);
    }

    @PatchMapping("/members/{id}")
    public Member updateMember(@PathVariable Long id, @RequestBody UpdateMemberDTO updateMemberDTO) {
        return updateMemberService.updateMember(id, updateMemberDTO);
    }

    @DeleteMapping("/members/{id}")
    public ResponseEntity<CommonResponse<Boolean>> deleteMember(@PathVariable Long id) {
        deleteMemberService.deleteMember(id);

        return new ResponseEntity<>(
                CommonResponse.succeed(true),
                HttpStatus.OK
        );
    }
}
