package com.hvnis.niscrum.converter;

import org.springframework.stereotype.Component;

import com.hvnis.niscrum.dto.UserDto;
import com.hvnis.niscrum.entity.UserEntity;

/**
 * @author hvnis
 */
@Component
public class UserConverter extends AbstractConverter<UserEntity, UserDto> {

    @Override
    public UserEntity toEntity(UserDto dto) {
        UserEntity entity = new UserEntity();
        return entity;
    }

    @Override
    public UserDto toDto(UserEntity enity) {
        return new UserDto();
    }

}
