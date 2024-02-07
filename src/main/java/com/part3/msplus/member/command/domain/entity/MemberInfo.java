package com.part3.msplus.member.command.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class MemberInfo {

    @Column(name = "member_id", length = 50, nullable = false)
    private String memberId;


    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "nickname", length = 50, nullable = false)
    private String nickname;

    @Column(name = "phone", length = 20)
    private String phone;
}
