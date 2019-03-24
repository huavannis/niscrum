package com.hvnis.niscrum.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/")
    @ResponseBody
    public List<UserDto> findAll() {
        return userService.findAll();
    }
}
