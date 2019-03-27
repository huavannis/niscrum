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

import com.hvnis.niscrum.entity.UserRoleEntity;
import com.hvnis.niscrum.repository.UserRoleRepository;

import lombok.AllArgsConstructor;

/**
 * @author hvnis
 */
@AllArgsConstructor
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UserRoleCache {

    private static Map<Long, UserRoleEntity> userRoleCache;

    private final UserRoleRepository userRoleRepository;

    @PostConstruct
    private void init() {
        userRoleCache = new HashMap<>();
        userRoleRepository.findAll().stream().forEach(this::putUserRole);
    }

    public void putUserRole(UserRoleEntity userRoleEntity) {
        userRoleCache.put(userRoleEntity.getId(), userRoleEntity);
    }

    public Optional<UserRoleEntity> getUserRole(Long id) {
        return Optional.ofNullable(userRoleCache.get(id));
    }

    public List<UserRoleEntity> getAllRoles(Long userId) {
        return userRoleCache.values().stream().filter(userRole -> userRole.getUserId().equals(userId))
                .collect(Collectors.toList());
    }
}
