package com.part3.member.query;


import com.part3.member.command.domain.entity.Member;
import com.part3.member.query.dao.MemberDAO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Getter
@Service
public class ReadMemberService {

    private final MemberDAO memberDAO;

    public Optional<Member> findById(Long id) {
        return memberDAO.findById(id);
    }
}

