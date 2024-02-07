package com.part3.msplus.member.command.domain.entity;

import com.part3.msplus.common.model.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
@Getter
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // pk 생성 전략 => identity
    private Long id;

    @Column(name = "member_id", length = 50, nullable = false)
    private String memberId;

    @Column(name = "password", length = 100, nullable = false)
    private Password password;

    @Column(name = "email", length = 100, nullable = false)
    private Email email;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "nickname", length = 50, nullable = false)
    private String nickname;

    @Column(name = "phone", length = 20)
    private String phone;

    private MemberInfo memberInfo;

    /**
     * eager loading, not null relationship
     */
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "member_role_id", referencedColumnName = "id")
    private MemberRole memberRole;

    @Builder
    private Member(Password password, Email email, MemberInfo memberInfo, MemberRole memberRole) {
        this.password = password;
        this.email = email;
        this.memberInfo = memberInfo;
        this.memberRole = memberRole;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", email=" + email +
                ", memberInfo=" + memberInfo +
                ", memberRole=" + memberRole +
                '}';
    }
}
