package com.hvnis.niscrum.jwt;

import java.util.Collection;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.hvnis.niscrum.entity.UserEntity;
import com.hvnis.niscrum.exception.TokenException;
import com.hvnis.niscrum.repository.UserRepository;
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

    private static final String ROLES_CLAIM_KEY = "roles";

    private static final String USER_ID_CLAIM_KEY = "userId";

    private final UserRepository userRepository;

    private final TokenProvider tokenProvider;

    public void addAuthentication(HttpServletResponse res, CustomAuthentication authentication) {
        final Claims claims = Jwts.claims();
        claims.put(ROLES_CLAIM_KEY, authentication.getAuthorities());
        claims.put(USER_ID_CLAIM_KEY, authentication.getUserEntity().map(UserEntity::getId).orElse(0L));
        String JWT = tokenProvider.generateToken(authentication.getName(), claims);
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
    }

    @SuppressWarnings("unchecked")
    public CustomAuthentication getAuthentication(HttpServletRequest request) throws TokenException {
        final String authorization = request.getHeader(HEADER_STRING);
        return Optional.ofNullable(authorization).map(value -> value.replace(TOKEN_PREFIX, "")).map(token -> {
            final Claims claims = tokenProvider.parseToken(token);
            Optional<Collection<GrantedAuthority>> rolesOptional = Optional
                    .ofNullable(claims.get(ROLES_CLAIM_KEY, Collection.class));
            Optional<Long> userIdOptional = Optional.ofNullable(claims.get(USER_ID_CLAIM_KEY, Long.class));
            if (rolesOptional.isPresent() && userIdOptional.isPresent()) {
                return userRepository.findById(userIdOptional.get()).map(
                        userEntity -> new CustomAuthentication(userEntity.getUsername(), null, rolesOptional.get()))
                        .orElse(null);
            }
            return null;

        }).orElse(null);
    }
}
