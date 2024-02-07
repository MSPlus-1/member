package com.part3.msplus.member.command.domain.entity;

import com.part3.msplus.common.model.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.context.annotation.Primary;

import java.util.Date;

@Entity
@Table(name = "member")
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

    /**
     * eager loading, not null relationship
     */
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "member_role_id", referencedColumnName = "id")
    private MemberRole memberRole;
}
