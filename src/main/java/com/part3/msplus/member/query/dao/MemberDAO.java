package com.part3.msplus.member.query.dao;

import com.part3.msplus.member.command.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberDAO extends JpaRepository<Member, Long>{

}

