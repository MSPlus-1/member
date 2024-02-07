package com.part3.msplus.member.command.domain.entity;

import com.part3.msplus.common.exception.CustomException;
import com.part3.msplus.common.exception.dto.Error;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import static com.part3.msplus.common.util.ByteUtils.bytesToHex;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Password {

    @Column(name = "password")
    private String value;

    @Column(name = "salt")
    private String salt;

    private Password(String value) {
        this.value = value;
        this.salt = this.generateSalt();
    }

    /**
     * hashing user password using SHA-256 hash algorithm
     * @param original unencrypted password
     * @param salt     salt that adding to original
     * @return encrypted password
     */
    public Password hashPassword(Password original, String salt) {
        String temp = original.value + salt;

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(temp.getBytes(StandardCharsets.UTF_8));
            return new Password(bytesToHex(hash));
        } catch (NoSuchAlgorithmException exception) {
            throw new CustomException(Error.INVALID_PASSWORD_ALGORITHM);
        }
    }

    /**
     * validate password
     * @param target Password entered from outside
     */
    public void validationMatchPassword(Password target) {
        if (!this.value.equals(target.value)) {
            throw new CustomException(Error.MISMATCH_PASSWORD);
        }
    }

    private String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);
        return bytesToHex(saltBytes);
    }
}
