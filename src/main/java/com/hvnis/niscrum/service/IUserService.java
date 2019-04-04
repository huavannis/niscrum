package com.hvnis.niscrum.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.hvnis.niscrum.common.PrivilegeConstant;
import com.hvnis.niscrum.dto.PrivilegeDto;
import com.hvnis.niscrum.dto.RoleDto;
import com.hvnis.niscrum.dto.UserDto;

/**
 * @author hvnis
 */
public interface IUserService {

    @PreAuthorize(PrivilegeConstant.PRIV_GET_ALL_USERS)
    List<UserDto> getAllUsers();

    @PreAuthorize(PrivilegeConstant.PRIV_GET_ALL_ROLES)
    List<RoleDto> getAllRoles();

    @PreAuthorize(PrivilegeConstant.PRIV_GET_ALL_PRIVILEGES)
    List<PrivilegeDto> getAllPrivileges();

    @PreAuthorize("hasRole('ROLE_ADMIN') or #userId == authentication.getUserId()")
    List<RoleDto> getAllRolesByUser(Long userId);

    @PreAuthorize("hasRole('ROLE_ADMIN') or authentication.hasRole(#roleId)")
    List<PrivilegeDto> getAllPrivilegesByRole(Long roleId);
}
