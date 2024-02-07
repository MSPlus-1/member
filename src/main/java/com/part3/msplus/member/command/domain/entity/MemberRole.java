package com.part3.msplus.member.command.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "member_role")
@Getter
public class MemberRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role", length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
}
