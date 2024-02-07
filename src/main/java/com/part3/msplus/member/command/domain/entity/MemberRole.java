package com.part3.msplus.member.command.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member_role")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MemberRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role", length = 50, nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private Role role;

    private MemberRole(Role role) {
        this.role = role;
    }

    public static MemberRole from(Role role) {
        return new MemberRole(role);
    }

    @Override
    public String toString() {
        return "MemberRole{" +
                "role=" + role +
                '}';
    }
}
