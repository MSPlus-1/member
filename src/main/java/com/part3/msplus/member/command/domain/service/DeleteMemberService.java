package com.part3.msplus.member.command.domain.service;

import com.part3.msplus.global.exception.CustomException;
import com.part3.msplus.global.exception.dto.Error;
import com.part3.msplus.member.command.domain.entity.Member;
import com.part3.msplus.member.command.domain.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteMemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void deleteMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND_MEMBER));
        memberRepository.delete(member);
    }
}
