package com.part3.msplus.member.command.domain.entity;

import com.part3.msplus.global.model.BaseTimeEntity;
import com.part3.msplus.member.controller.dto.request.UpdateMemberDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Objects;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // pk 생성 전략 => identity
    private Long id;

    @Embedded
    private Password password;

    @Embedded
    private Email email;

    @Embedded
    private MemberInfo memberInfo;

    @Enumerated(value = EnumType.STRING)
    private AuthProvider authProvider;

    /**
     * eager loading, not null relationship
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "member_role_id", referencedColumnName = "id")
    private MemberRole memberRole;

    @Builder
    private Member(Password password, Email email, MemberInfo memberInfo, MemberRole memberRole, AuthProvider authProvider) {
        this.password = password;
        this.email = email;
        this.memberInfo = memberInfo;
        this.memberRole = memberRole;
        this.authProvider = authProvider;
    }

    public void update(UpdateMemberDTO updateMemberDTO) {
        this.email = Email.from(updateMemberDTO.email());
        this.password = Password.from(updateMemberDTO.password());

        this.memberInfo = MemberInfo.of(
            updateMemberDTO.memberId(),
            updateMemberDTO.name(),
            updateMemberDTO.nickname(),
            updateMemberDTO.phone()
        );
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

    @Override
    public int hashCode() {
        return Objects.hash(id, password, email, memberInfo, authProvider, memberRole);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(id, member.id) && Objects.equals(password, member.password) && Objects.equals(email, member.email) && Objects.equals(memberInfo, member.memberInfo) && authProvider == member.authProvider && Objects.equals(memberRole, member.memberRole);
    }
}
