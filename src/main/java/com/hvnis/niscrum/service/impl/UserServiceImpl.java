package com.hvnis.niscrum.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hvnis.niscrum.converter.UserConverter;
import com.hvnis.niscrum.dto.UserDto;
import com.hvnis.niscrum.repository.UserRepository;
import com.hvnis.niscrum.service.IUserService;

import lombok.AllArgsConstructor;

/**
 * @author hvnis
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    private final UserConverter userConverter;

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(userConverter::toDto).collect(Collectors.toList());
    }
}
