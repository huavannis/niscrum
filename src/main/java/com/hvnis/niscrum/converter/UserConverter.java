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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateEntity(UserEntity entity, UserDto dto) {
        // TODO Auto-generated method stub

    }

    @Override
    public UserDto toDto(UserEntity enity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateDto(UserDto dto, UserEntity entity) {
        // TODO Auto-generated method stub

    }
}
