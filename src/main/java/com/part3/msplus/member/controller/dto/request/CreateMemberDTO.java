package com.part3.msplus.member.controller.dto.request;


public record CreateMemberDTO(String name, String email, String password, String phone, String nickname, String memberId) {

}
