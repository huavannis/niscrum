package com.hvnis.niscrum.cache;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.hvnis.niscrum.entity.PrivilegeEntity;
import com.hvnis.niscrum.entity.RoleEntity;
import com.hvnis.niscrum.entity.UserEntity;
import com.hvnis.niscrum.repository.UserRepository;

import lombok.AllArgsConstructor;

/**
 * @author hvnis
 */
@AllArgsConstructor
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UserCache {

    private static Map<Long, UserEntity> userByIdCache;
    private static Map<String, UserEntity> userByUsernameCache;
    private static Map<UserEntity, Set<GrantedAuthority>> authoritiesCache;

    private final UserRepository userRepository;

    private final RoleCache roleCache;

    private final UserRoleCache userRoleCache;

    private final PrivilegeCache privilegeCache;

    private final RolePrivilegeCache rolePrivilegeCache;

    @PostConstruct
    private void init() {
        userByIdCache = new HashMap<>();
        userByUsernameCache = new HashMap<>();
        authoritiesCache = new HashMap<>();
        userRepository.findAll().stream().forEach(this::putUser);
    }

    public Optional<UserEntity> getUserById(Long id) {
        return Optional.ofNullable(userByIdCache.get(id));
    }

    public Optional<UserEntity> getUserByUsername(String username) {
        return Optional.ofNullable(userByUsernameCache.get(username));
    }

    public void putUser(UserEntity userEntity) {
        Optional.ofNullable(userEntity).ifPresent(user -> {
            userByIdCache.put(user.getId(), user);
            userByUsernameCache.put(user.getUsername(), user);
            updateAuthorities(user);
        });
    }

    public Set<GrantedAuthority> getAuthoritiesFromCache(UserEntity userEntity) {
        return authoritiesCache.get(userEntity);
    }

    public void updateAuthorities(UserEntity userEntity) {
        Optional.ofNullable(userEntity).ifPresent(user -> authoritiesCache.put(user, getAuthorities(user)));
    }

    private Set<GrantedAuthority> getAuthorities(UserEntity userEntity) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        Optional.ofNullable(userEntity).ifPresent(user -> {
            userRoleCache.getAllRoles(user.getId()).stream().forEach(userRole -> {
                roleCache.getRole(userRole.getRoleId()).ifPresent(role -> {
                    authorities.addAll(getAuthorities(role));
                });
            });
        });
        return authorities;
    }

    private Set<GrantedAuthority> getAuthorities(RoleEntity roleEntity) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        Optional.ofNullable(roleEntity).ifPresent(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
            rolePrivilegeCache.getAllPrivileges(role.getId()).stream().forEach(rolePrivilege -> {
                privilegeCache.getPrivilege(rolePrivilege.getPrivilegeId()).ifPresent(privilege -> {
                    authorities.addAll(getAuthorities(privilege));
                });
            });
        });
        return authorities;
    }

    private Set<GrantedAuthority> getAuthorities(PrivilegeEntity privilegeEntity) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        Optional.ofNullable(privilegeEntity).ifPresent(privilege -> {
            authorities.add(new SimpleGrantedAuthority(privilege.getName()));
        });
        return authorities;
    }
}
