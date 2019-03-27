package com.hvnis.niscrum.security;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.hvnis.niscrum.cache.UserCache;
import com.hvnis.niscrum.encoder.SHA256Encoder;
import com.hvnis.niscrum.entity.UserEntity;

import lombok.AllArgsConstructor;

/**
 * @author hvnis
 */
@AllArgsConstructor
@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    private final UserCache userCache;

    private final SHA256Encoder sha256Encoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return Optional.ofNullable(authentication).map(auth -> (CustomAuthentication) auth)
                .map(customAuthentication -> {
                    Optional<String> usernameOptional = customAuthentication.getUsername();
                    Optional<String> passwordOptional = customAuthentication.getPassword();
                    if (usernameOptional.isPresent() && passwordOptional.isPresent()) {
                        String username = usernameOptional.get();
                        String password = passwordOptional.get();
                        Optional<UserEntity> userEntityOptional = userCache.getUserByUsernameFromCache(username);
                        if (userEntityOptional.isPresent()) {
                            UserEntity userEntity = userEntityOptional.get();
                            if (sha256Encoder.encode(password, userEntity.getSalt()).equals(userEntity.getPassword())) {
                                return new CustomAuthentication(username, password,
                                        userCache.getAuthoritiesFromCache(userEntity), userEntity, true);
                            }
                        }
                    }
                    throw new BadCredentialsException("Bad Credentials");
                }).orElse(null);
    }
}
