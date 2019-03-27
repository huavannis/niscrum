package com.hvnis.niscrum.jwt;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.hvnis.niscrum.exception.TokenRuntimeException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author hvnis
 */
@Component
public class TokenProvider {

    static final long EXPIRATIONTIME = 864_000_000; // 10 days

    static final String SECRET = "ThisIsASecret";

    public String generateToken(final String subject, final Claims claims) {
        return Jwts.builder().setSubject(subject).setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .setClaims(claims).signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }

    public Claims parseToken(final String token) throws TokenRuntimeException {
        try {
            return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        } catch (Exception ex) {
            throw new TokenRuntimeException(ex);
        }
    }
}
