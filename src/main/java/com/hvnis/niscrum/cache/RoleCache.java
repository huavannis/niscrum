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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.hvnis.niscrum.common.Constant;
import com.hvnis.niscrum.entity.PrivilegeEntity;
import com.hvnis.niscrum.entity.RoleEntity;
import com.hvnis.niscrum.entity.RolePrivilegeEntity;
import com.hvnis.niscrum.repository.RolePrivilegeRepository;
import com.hvnis.niscrum.repository.RoleRepository;

import lombok.AllArgsConstructor;

/**
 * @author hvnis
 */
@AllArgsConstructor
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class RoleCache {

    private static Map<Long, RoleEntity> roleCache;
    private static Map<RoleEntity, Set<PrivilegeEntity>> rolePrivilegesCache;
    private static Map<RoleEntity, Set<GrantedAuthority>> roleAuthoritiesCache;

    private final RoleRepository roleRepository;

    private final RolePrivilegeRepository rolePrivilegeRepository;

    private final PrivilegeCache privilegeCache;

    @PostConstruct
    private void init() {
        roleCache = new HashMap<>();
        rolePrivilegesCache = new HashMap<>();
        roleAuthoritiesCache = new HashMap<>();
        roleRepository.findAll().forEach(this::updateRole);
    }

    public void updateRole(RoleEntity roleEntity) {
        Optional.ofNullable(roleEntity).ifPresent(role -> {
            roleCache.put(roleEntity.getId(), roleEntity);
            updateRolePrivileges(roleEntity);
            updateRoleAuthorities(roleEntity);
        });
    }

    private void updateRolePrivileges(RoleEntity roleEntity) {
        Optional.ofNullable(roleEntity).ifPresent(role -> {
            Set<PrivilegeEntity> privileges = rolePrivilegeRepository.findAllByRoleId(role.getId()).stream()
                    .map(RolePrivilegeEntity::getId).map(privilegeCache::getPrivilege).filter(Optional::isPresent)
                    .map(Optional::get).collect(Collectors.toSet());
            rolePrivilegesCache.put(role, privileges);
        });
    }

    private void updateRoleAuthorities(RoleEntity roleEntity) {
        Optional.ofNullable(roleEntity).ifPresent(role -> {
            Set<GrantedAuthority> authorities = new HashSet<>();
            authorities.add(new SimpleGrantedAuthority(Constant.SPRING_SECURITY_ROLE_PREFIX + role.getName()));
            getRolePrivileges(role).stream().map(privilegeCache::getAuthority).filter(Optional::isPresent)
                    .map(Optional::get).forEach(authorities::add);
            roleAuthoritiesCache.put(role, authorities);
        });
    }

    public Set<RoleEntity> getAllRoles() {
        return roleCache.values().stream().collect(Collectors.toSet());
    }

    public Optional<RoleEntity> getRole(Long id) {
        return Optional.ofNullable(roleCache.get(id));
    }

    public Set<PrivilegeEntity> getRolePrivileges(RoleEntity roleEntity) {
        return Optional.ofNullable(rolePrivilegesCache.get(roleEntity)).orElse(new HashSet<>());
    }

    public Set<GrantedAuthority> getRoleAuthorities(RoleEntity roleEntity) {
        return Optional.ofNullable(roleAuthoritiesCache.get(roleEntity)).orElse(new HashSet<>());
    }
}
