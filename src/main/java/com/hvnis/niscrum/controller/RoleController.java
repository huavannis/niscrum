package com.hvnis.niscrum.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hvnis.niscrum.dto.PrivilegeDto;
import com.hvnis.niscrum.dto.RoleDto;
import com.hvnis.niscrum.service.IUserService;

import lombok.AllArgsConstructor;

/**
 * @author hvnis
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/roles")
public class RoleController extends AbstractController {

    private final IUserService userService;

    @RequestMapping
    public List<RoleDto> getAllRoles() {
        return userService.getAllRoles();
    }

    @RequestMapping(value = "/{roleId}/privileges", method = RequestMethod.GET)
    public List<PrivilegeDto> getAllPrivilegesByRole(@PathVariable Long roleId) {
        return userService.getAllPrivilegesByRole(roleId);
    }
}
