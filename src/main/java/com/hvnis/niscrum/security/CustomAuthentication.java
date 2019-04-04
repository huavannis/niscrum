package com.hvnis.niscrum.security;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import com.hvnis.niscrum.entity.RoleEntity;

import lombok.Getter;

/**
 * @author hvnis
 */
@Getter
public class CustomAuthentication extends UsernamePasswordAuthenticationToken {

    private static final long serialVersionUID = 5675435368571601125L;

    private Long userId;

    private Set<RoleEntity> roles;

    public CustomAuthentication(String principal, String credentials) {
        this(principal, credentials, Collections.emptyList());
    }

    public CustomAuthentication(String principal, String credentials,
            Collection<? extends GrantedAuthority> authorities) {
        this(principal, credentials, authorities, 0L, Collections.emptySet(), false);
    }

    public CustomAuthentication(String principal, String credentials,
            Collection<? extends GrantedAuthority> authorities, Long userId, Set<RoleEntity> roles,
            boolean isAuthenticated) {
        super(principal, credentials, authorities);
        this.userId = userId;
        this.roles = roles;
        this.setAuthenticated(isAuthenticated);
    }

    public Optional<String> getUsername() {
        return Optional.ofNullable(getPrincipal()).map(String::valueOf);
    }

    public Optional<String> getPassword() {
        return Optional.ofNullable(getCredentials()).map(String::valueOf);
    }

    public Long getUserId() {
        return userId;
    }

    public boolean hasRole(Long roleId) {
        return roles.stream().map(RoleEntity::getId).anyMatch(role -> role == roleId);
    }
}
