package com.part3.msplus.member.command.domain.repository;

import com.part3.msplus.member.command.domain.entity.MemberRole;
import com.part3.msplus.member.command.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRoleRepository extends JpaRepository<MemberRole, Long> {

    Optional<MemberRole> findByRole(Role role);
}
