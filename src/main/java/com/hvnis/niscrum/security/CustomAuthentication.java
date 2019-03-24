package com.hvnis.niscrum.security;

import java.util.Collection;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import com.hvnis.niscrum.entity.UserEntity;

import lombok.Getter;

/**
 * @author hvnis
 */
@Getter
public class CustomAuthentication extends UsernamePasswordAuthenticationToken {

    private static final long serialVersionUID = 5675435368571601125L;
    
    private Optional<UserEntity> userEntity;

    public CustomAuthentication(String principal, String credentials) {
        super(principal, credentials);
    }
    
    public CustomAuthentication(String principal, String credentials,
            Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public CustomAuthentication(String principal, String credentials,
            Collection<? extends GrantedAuthority> authorities, UserEntity userEntity, boolean isAuthenticated) {
        super(principal, credentials, authorities);
        this.userEntity = Optional.ofNullable(userEntity);
        this.setAuthenticated(isAuthenticated);
    }
    
    public Optional<String> getUsername() {
        return Optional.ofNullable(getPrincipal()).map(String::valueOf);
    }
    
    public Optional<String> getPassword() {
        return Optional.ofNullable(getCredentials()).map(String::valueOf);
    }
}
