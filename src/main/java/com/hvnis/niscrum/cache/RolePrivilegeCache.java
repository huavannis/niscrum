package com.hvnis.niscrum.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.hvnis.niscrum.entity.RolePrivilegeEntity;
import com.hvnis.niscrum.repository.RolePrivilegeRepository;

import lombok.AllArgsConstructor;

/**
 * @author hvnis
 */
@AllArgsConstructor
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class RolePrivilegeCache {

    private static Map<Long, RolePrivilegeEntity> rolePrivilegeCache;

    private final RolePrivilegeRepository rolePrivilegeRepository;

    @PostConstruct
    private void init() {
        rolePrivilegeCache = new HashMap<>();
        rolePrivilegeRepository.findAll().stream().forEach(this::putRolePrivilege);
    }

    public void putRolePrivilege(RolePrivilegeEntity rolePrivilegeEntity) {
        rolePrivilegeCache.put(rolePrivilegeEntity.getId(), rolePrivilegeEntity);
    }

    public Optional<RolePrivilegeEntity> getRolePrivilege(Long id) {
        return Optional.ofNullable(rolePrivilegeCache.get(id));
    }

    public List<RolePrivilegeEntity> getAllPrivileges(Long roleId) {
        return rolePrivilegeCache.values().stream().filter(rolePrivilege -> rolePrivilege.getRoleId().equals(roleId))
                .collect(Collectors.toList());
    }
}
