package com.hvnis.niscrum.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hvnis.niscrum.cache.PrivilegeCache;
import com.hvnis.niscrum.cache.RoleCache;
import com.hvnis.niscrum.cache.UserCache;
import com.hvnis.niscrum.converter.PrivilegeConverter;
import com.hvnis.niscrum.converter.RoleConverter;
import com.hvnis.niscrum.converter.UserConverter;
import com.hvnis.niscrum.dto.PrivilegeDto;
import com.hvnis.niscrum.dto.RoleDto;
import com.hvnis.niscrum.dto.UserDto;
import com.hvnis.niscrum.entity.RoleEntity;
import com.hvnis.niscrum.entity.UserEntity;
import com.hvnis.niscrum.service.IUserService;

import lombok.AllArgsConstructor;

/**
 * @author hvnis
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserCache userCache;

    private final RoleCache roleCache;

    private final PrivilegeCache privilegeCache;

    private final UserConverter userConverter;

    private final RoleConverter roleConverter;

    private final PrivilegeConverter privilegeConverter;

    @Override
    public List<UserDto> getAllUsers() {
        return userCache.getAllUsers().stream().map(userConverter::toDto).collect(Collectors.toList());
    }

    @Override
    public List<RoleDto> getAllRoles() {
        return roleCache.getAllRoles().stream().map(roleConverter::toDto).collect(Collectors.toList());
    }

    @Override
    public List<PrivilegeDto> getAllPrivileges() {
        return privilegeCache.getAllPrivileges().stream().map(privilegeConverter::toDto).collect(Collectors.toList());
    }

    @Override
    public List<RoleDto> getAllRolesByUser(Long userId) {
        Optional<UserEntity> userEntity = userCache.getUserById(userId);
        if (userEntity.isPresent()) {
            return userCache.getUserRoles(userEntity.get()).stream().map(roleConverter::toDto)
                    .collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<PrivilegeDto> getAllPrivilegesByRole(Long roleId) {
        Optional<RoleEntity> roleEntity = roleCache.getRole(roleId);
        if (roleEntity.isPresent()) {
            return roleCache.getRolePrivileges(roleEntity.get()).stream().map(privilegeConverter::toDto)
                    .collect(Collectors.toList());
        }
        return null;
    }
}
