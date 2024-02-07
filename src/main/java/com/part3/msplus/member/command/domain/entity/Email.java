package com.part3.msplus.member.command.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Email {

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    /**
     * TODO : email 검증 등
     */
}
