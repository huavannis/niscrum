package com.hvnis.niscrum.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
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

    private final PrivilegeRepository privilegeRepository;

    @PostConstruct
    private void init() {
        privilegeCache = new HashMap<>();
        privilegeRepository.findAll().stream().forEach(this::putPrivilegeToCache);
    }

    public void putPrivilegeToCache(PrivilegeEntity privilegeEntity) {
        privilegeCache.put(privilegeEntity.getId(), privilegeEntity);
    }

    public Optional<PrivilegeEntity> getPrivilegeFromCache(Long id) {
        return Optional.ofNullable(privilegeCache.get(id));
    }
}
