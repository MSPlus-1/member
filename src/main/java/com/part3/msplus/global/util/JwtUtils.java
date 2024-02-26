package com.part3.msplus.global.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.json.JsonParser;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

public class JwtUtils {

    private static final Long EXPIRED_TIME = 1800000L; // 30MIN

    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private static final JsonParser jsonParser = new BasicJsonParser();

    public static String createJwt(String subject, String email) {
        Claims claims = Jwts.claims().setSubject(subject);
        claims.put("email", email);

        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + EXPIRED_TIME))
                .signWith(key)
                .compact();
    }

    public static Map<String, Object> getPayloadMap(String jwt) {
        String payload = jwt.split("\\.")[1];

        Base64.Decoder decoder = Base64.getDecoder();

        return jsonParser.parseMap(new String(decoder.decode(payload)));
    }

    /**
     * Validate jwt
     */
    public static Map<String, Object> parseJwt(String jwt) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt);
            return claims.getBody();
        } catch (UnsupportedJwtException e) {
            throw new UnsupportedJwtException(e.getMessage());
        } catch (MalformedJwtException e) {
            throw new MalformedJwtException(e.getMessage());
        } catch (ExpiredJwtException e) {
            throw new ExpiredJwtException(null, null, e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (SecurityException e) {
            throw new SecurityException(e.getMessage());
        }
    }
}