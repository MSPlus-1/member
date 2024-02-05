package com.part3.msplus.member.command.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.springframework.context.annotation.Primary;

import java.util.Date;

@Entity
@Table(name = "member")
@Getter
public class Member {

    @Id
    private Long id;

    @Column(name = "member_id", length = 50, nullable = false)
    private String memberId;

    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "nickname", length = 50, nullable = false)
    private String nickname;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "create_at", length = 20, nullable = false)
    private Date createdDate;

    @Column(name = "update_at", length = 20, nullable = false)
    private Date updatedDate;

    @Column(name = "delete_at", length = 20)
    private Date deletedDate;
}
