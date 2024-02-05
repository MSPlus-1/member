package com.part3.msplus.member.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MemberContorller {

    @PostMapping("/members")
    public Map<String, String> postMember() {

        return Map.of("message", "Hello, World!");
    }

    @GetMapping("/members")
    public Map<String, String> getMembers() {
        return Map.of("message", "Hello, World!");
    }

    @GetMapping("/members/{id}")
    public Map<String, String> getMember() {
        return Map.of("message", "Hello, World!");
    }
}
