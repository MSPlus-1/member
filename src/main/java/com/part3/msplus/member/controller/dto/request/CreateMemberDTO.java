package com.part3.msplus.member.controller.dto.request;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateMemberDTO {

    private final String name;
    private final String email;
    private final String password;
    private final String phone;
    private final String nickname;
    private final String memberId;

}
