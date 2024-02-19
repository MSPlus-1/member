package com.part3.msplus.member.command.domain.entity;

public enum Role {
    ROLE_ADMIN("관리자", "admin"),
    ROLE_USER("사용자", "user"),
    ROLE_GUEST("손님", "guest")
    ;

    private final String ko;
    private final String en;

    Role(String ko, String en) {
        this.ko = ko;
        this.en = en;
    }
}
