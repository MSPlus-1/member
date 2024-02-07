package com.part3.msplus.member.command.domain.service;

import com.part3.msplus.member.command.domain.entity.Member;
import com.part3.msplus.member.command.domain.repository.MemberRepository;
import com.part3.msplus.member.query.dao.MemberDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreateMemberService {

    private final MemberRepository memberRepository;

    public Member createMember(Member member) {
        return memberRepository.save(member);
    }
}
