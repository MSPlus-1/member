package com.part3.msplus.member.command.domain.service;


import com.part3.msplus.global.exception.CustomException;
import com.part3.msplus.member.command.domain.entity.Member;
import com.part3.msplus.member.command.domain.repository.MemberRepository;
import com.part3.msplus.member.controller.dto.request.UpdateMemberDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.part3.msplus.global.exception.dto.Error;


@Service
@RequiredArgsConstructor
public class UpdateMemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Member updateMember(Long id, UpdateMemberDTO updateMemberDTO) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND_MEMBER));
        member.update(updateMemberDTO);
        return member;
    }
}
