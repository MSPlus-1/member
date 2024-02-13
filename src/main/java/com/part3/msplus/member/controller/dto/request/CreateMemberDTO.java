package com.part3.msplus.member.controller.dto.request;


import lombok.Getter;
import lombok.RequiredArgsConstructor;


public record CreateMemberDTO(String name, String email, String password, String phone, String nickname, String memberId) {

}
