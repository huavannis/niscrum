package com.hvnis.niscrum.service;

import java.util.List;

import com.hvnis.niscrum.dto.UserDto;

/**
 * @author hvnis
 */
public interface IUserService {

	public List<UserDto> findAll();
}
