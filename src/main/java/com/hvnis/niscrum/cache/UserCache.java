package com.hvnis.niscrum.cache;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.hvnis.niscrum.entity.RoleEntity;
import com.hvnis.niscrum.entity.UserEntity;
import com.hvnis.niscrum.entity.UserRoleEntity;
import com.hvnis.niscrum.repository.UserRepository;
import com.hvnis.niscrum.repository.UserRoleRepository;

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
    private static Map<UserEntity, Set<RoleEntity>> userRolesCache;
    private static Map<UserEntity, Set<GrantedAuthority>> userAuthoritiesCache;

    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;

    private final RoleCache roleCache;

    @PostConstruct
    private void init() {
        userByIdCache = new HashMap<>();
        userByUsernameCache = new HashMap<>();
        userAuthoritiesCache = new HashMap<>();
        userRepository.findAll().forEach(this::updateUser);
    }

    public void updateUser(UserEntity userEntity) {
        Optional.ofNullable(userEntity).ifPresent(user -> {
            userByIdCache.put(user.getId(), user);
            userByUsernameCache.put(user.getUsername(), user);
            updateUserRoles(user);
            updateUserAuthorities(user);
        });
    }

    private void updateUserRoles(UserEntity userEntity) {
        Optional.ofNullable(userEntity).ifPresent(user -> {
            Set<RoleEntity> roles = userRoleRepository.findAllByUserId(user.getId()).stream()
                    .map(UserRoleEntity::getRoleId).map(roleCache::getRole).filter(Optional::isPresent)
                    .map(Optional::get).collect(Collectors.toSet());
            userRolesCache.put(user, roles);
        });
    }

    private void updateUserAuthorities(UserEntity userEntity) {
        Optional.ofNullable(userEntity).ifPresent(user -> {
            Set<GrantedAuthority> authorities = new HashSet<>();
            getUserRoles(user).stream().map(roleCache::getRoleAuthorities).forEach(authorities::addAll);
            userAuthoritiesCache.put(user, authorities);
        });
    }

    public Set<UserEntity> getAllUsers() {
        return userByIdCache.values().stream().collect(Collectors.toSet());
    }

    public Optional<UserEntity> getUserById(Long id) {
        return Optional.ofNullable(userByIdCache.get(id));
    }

    public Optional<UserEntity> getUserByUsername(String username) {
        return Optional.ofNullable(userByUsernameCache.get(username));
    }

    public Set<RoleEntity> getUserRoles(UserEntity userEntity) {
        return Optional.ofNullable(userRolesCache.get(userEntity)).orElse(new HashSet<>());
    }

    public Set<GrantedAuthority> getUserAuthorities(UserEntity userEntity) {
        return Optional.ofNullable(userAuthoritiesCache.get(userEntity)).orElse(new HashSet<>());
    }
}
