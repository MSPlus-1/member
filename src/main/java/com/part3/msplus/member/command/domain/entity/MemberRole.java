package com.part3.msplus.member.command.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "member_role")
@Getter
public class MemberRole {

    @Id
    private Long id;

    @Column(name = "member_id", length = 50, nullable = false)
    private String name;
}
