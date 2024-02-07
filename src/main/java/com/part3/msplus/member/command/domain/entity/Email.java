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
public class Email {

    @Column(name = "email", length = 100, nullable = false)
    private String value;

    private Email(String value) {
        this.value = value;
    }

    public static Email from(String value) {
        if (!value.contains("@")) {
            throw new CustomException(Error.INVALID_EMAIL_FORMAT);
        }

        return new Email(value);
    }

    @Override
    public String toString() {
        return "Email{" +
                "value='" + value + '\'' +
                '}';
    }
}
