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
        this.salt = this.generateSalt();
        this.value = hashPassword(value, this.salt);
    }

    public static Password from(String value) {
        if (value.length() < 8) {
            throw new CustomException(Error.INVALID_PASSWORD_FORMAT);
        }

        return new Password(value);
    }

    /**
     * validate password
     * @param target Password entered from outside
     */
    public void validationMatchPassword(String target) {
        if (!this.value.equals(hashPassword(target, this.salt))) {
            throw new CustomException(Error.MISMATCH_PASSWORD);
        }
    }

    /**
     * hashing user password using SHA-256 hash algorithm
     * @param original unencrypted password
     * @param salt     salt that adding to original
     * @return encrypted password
     */
    private String hashPassword(String original, String salt) {
        String temp = original + salt;

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(temp.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hash);
        } catch (NoSuchAlgorithmException exception) {
            throw new CustomException(Error.INVALID_PASSWORD_ALGORITHM);
        }
    }

    private String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);
        return bytesToHex(saltBytes);
    }
}
