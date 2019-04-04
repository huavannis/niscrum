package com.hvnis.niscrum.jwt;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.hvnis.niscrum.cache.UserCache;
import com.hvnis.niscrum.exception.TokenRuntimeException;
import com.hvnis.niscrum.security.CustomAuthentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;

/**
 * @author hvnis
 */
@AllArgsConstructor
@Service
public final class TokenAuthenticationService {

    private static final String TOKEN_PREFIX = "Bearer";

    private static final String HEADER_STRING = "Authorization";

    private static final String USER_ID_CLAIM_KEY = "userId";

    private final UserCache userCache;

    private final TokenProvider tokenProvider;

    public void addAuthentication(HttpServletResponse res, CustomAuthentication authentication) {
        final Claims claims = Jwts.claims();
        claims.put(USER_ID_CLAIM_KEY, authentication.getUserId());
        String JWT = tokenProvider.generateToken(authentication.getName(), claims);
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
    }

    public CustomAuthentication getAuthentication(HttpServletRequest request) throws TokenRuntimeException {
        final String authorization = request.getHeader(HEADER_STRING);
        return Optional.ofNullable(authorization).map(value -> value.replace(TOKEN_PREFIX, "")).map(token -> {
            final Claims claims = tokenProvider.parseToken(token);
            Optional<Long> userIdOptional = Optional.ofNullable(claims.get(USER_ID_CLAIM_KEY, Long.class));
            if (userIdOptional.isPresent()) {
                return userCache.getUserById(userIdOptional.get())
                        .map(userEntity -> new CustomAuthentication(userEntity.getUsername(), null,
                                userCache.getUserAuthorities(userEntity)))
                        .orElse(null);
            }
            return null;

        }).orElse(null);
    }
}
