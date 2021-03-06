package com.hvnis.niscrum.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hvnis.niscrum.dto.RoleDto;
import com.hvnis.niscrum.dto.UserDto;
import com.hvnis.niscrum.service.IUserService;

import lombok.AllArgsConstructor;

/**
 * @author hvnis
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController extends AbstractController {

    private final IUserService userService;

    @RequestMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/{userId}/roles", method = RequestMethod.GET)
    public List<RoleDto> getAllRolesByUser(@PathVariable Long userId) {
        return userService.getAllRolesByUser(userId);
    }
}
