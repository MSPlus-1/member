package com.part3.msplus.member.query;

import com.part3.msplus.member.command.domain.entity.Member;
import com.part3.msplus.member.query.dao.MemberDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReadMemberService {

    private final MemberDAO memberDAO;

    public Optional<Member> findById(Long id) {
        return memberDAO.findById(id);
    }

    public List<Member> findAll() {
        return memberDAO.findAll();
    }
}
