package com.hvnis.niscrum.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hvnis.niscrum.dto.PrivilegeDto;
import com.hvnis.niscrum.service.IUserService;

import lombok.AllArgsConstructor;

/**
 * @author hvnis
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/privileges")
public class PrivilegeController extends AbstractController {

    private final IUserService userService;

    @RequestMapping
    public List<PrivilegeDto> getAllPrivileges() {
        return userService.getAllPrivileges();
    }
}
