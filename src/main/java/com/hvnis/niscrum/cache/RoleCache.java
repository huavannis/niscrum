package com.hvnis.niscrum.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.hvnis.niscrum.entity.RoleEntity;
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

    private final RoleRepository roleRepository;

    @PostConstruct
    private void init() {
        roleCache = new HashMap<>();
        roleRepository.findAll().stream().forEach(this::putRole);
    }

    public void putRole(RoleEntity roleEntity) {
        roleCache.put(roleEntity.getId(), roleEntity);
    }

    public Optional<RoleEntity> getRole(Long id) {
        return Optional.ofNullable(roleCache.get(id));
    }
}
