package com.part3.msplus.member.command.domain.repository;

import com.part3.msplus.member.command.domain.entity.Email;
import com.part3.msplus.member.command.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberCustomRepository{
    // MemberRepository extends JpaRepository, which is a Spring Data interface that provides CRUD operations for the entity type that is being managed.
    Optional<Member> findByEmail(Email email);
}
