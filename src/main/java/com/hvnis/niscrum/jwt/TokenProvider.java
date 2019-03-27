package com.hvnis.niscrum.jwt;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.hvnis.niscrum.confprop.JWTConfigProperties;
import com.hvnis.niscrum.exception.TokenRuntimeException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;

/**
 * @author hvnis
 */
@AllArgsConstructor
@Component
public class TokenProvider {

    private final JWTConfigProperties jwtConfigProperties;

    public String generateToken(final String subject, final Claims claims) {
        return Jwts.builder().setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfigProperties.getExpirationTime()))
                .setClaims(claims).signWith(SignatureAlgorithm.HS512, jwtConfigProperties.getSecret()).compact();
    }

    public Claims parseToken(final String token) throws TokenRuntimeException {
        try {
            return Jwts.parser().setSigningKey(jwtConfigProperties.getSecret()).parseClaimsJws(token).getBody();
        } catch (Exception ex) {
            throw new TokenRuntimeException(ex);
        }
    }
}
