package com.hvnis.niscrum.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.hvnis.niscrum.entity.PrivilegeEntity;
import com.hvnis.niscrum.repository.PrivilegeRepository;

import lombok.AllArgsConstructor;

/**
 * @author hvnis
 */
@AllArgsConstructor
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PrivilegeCache {

    private static Map<Long, PrivilegeEntity> privilegeCache;
    private static Map<PrivilegeEntity, GrantedAuthority> authorityCache;

    private final PrivilegeRepository privilegeRepository;

    @PostConstruct
    private void init() {
        privilegeCache = new HashMap<>();
        authorityCache = new HashMap<>();
        privilegeRepository.findAll().stream().forEach(this::updatePrivilege);
    }

    public void updatePrivilege(PrivilegeEntity privilegeEntity) {
        Optional.ofNullable(privilegeEntity).ifPresent(privilege -> {
            privilegeCache.put(privilegeEntity.getId(), privilegeEntity);
            updateAuthority(privilege);
        });
    }

    private void updateAuthority(PrivilegeEntity privilegeEntity) {
        Optional.ofNullable(privilegeEntity).map(
                privilege -> authorityCache.put(privilege, new SimpleGrantedAuthority("PRIV_" + privilege.getName())));
    }

    public Optional<PrivilegeEntity> getPrivilege(Long id) {
        return Optional.ofNullable(privilegeCache.get(id));
    }

    public Optional<GrantedAuthority> getAuthority(PrivilegeEntity privilegeEntity) {
        return Optional.ofNullable(authorityCache.get(privilegeEntity));
    }
}
