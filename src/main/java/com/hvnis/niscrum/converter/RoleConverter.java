package com.hvnis.niscrum.converter;

import org.springframework.stereotype.Component;

import com.hvnis.niscrum.dto.RoleDto;
import com.hvnis.niscrum.entity.RoleEntity;

/**
 * @author hvnis
 */
@Component
public class RoleConverter extends AbstractConverter<RoleEntity, RoleDto> {

    @Override
    public RoleEntity toEntity(RoleDto dto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateEntity(RoleEntity entity, RoleDto dto) {
        // TODO Auto-generated method stub

    }

    @Override
    public RoleDto toDto(RoleEntity enity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateDto(RoleDto dto, RoleEntity entity) {
        // TODO Auto-generated method stub

    }
}
