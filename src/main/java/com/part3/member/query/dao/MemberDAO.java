package com.part3.member.query.dao;

import com.part3.member.command.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberDAO extends JpaRepository<Member, Long>, MemberCustomDAO{

}

