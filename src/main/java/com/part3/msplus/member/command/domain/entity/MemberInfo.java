package com.part3.msplus.member.command.domain.entity;

import com.part3.msplus.common.exception.CustomException;
import com.part3.msplus.common.exception.dto.Error;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MemberInfo {

    @Column(name = "member_id", length = 50, nullable = false)
    private String memberId;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "nickname", length = 50, nullable = false)
    private String nickname;

    @Column(name = "phone", length = 20)
    private String phone; /* TODO : 임베드 */

    private MemberInfo(String memberId, String name, String nickname, String phone) {
        this.memberId = memberId;
        this.name = name;
        this.nickname = nickname;
        this.phone = phone;
    }

    public static MemberInfo of(String memberId, String name, String nickname, String phone) {
        if (memberId == null || name == null || nickname == null) {
            throw new CustomException(Error.INVALID_INPUT_VALUE);
        }

        return new MemberInfo(memberId, name, nickname, phone);
    }

    @Override
    public String toString() {
        return "MemberInfo{" +
                "memberId='" + memberId + '\'' +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
