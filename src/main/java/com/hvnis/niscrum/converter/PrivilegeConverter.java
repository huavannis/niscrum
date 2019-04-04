package com.hvnis.niscrum.converter;

import org.springframework.stereotype.Component;

import com.hvnis.niscrum.dto.PrivilegeDto;
import com.hvnis.niscrum.entity.PrivilegeEntity;

/**
 * @author hvnis
 */
@Component
public class PrivilegeConverter extends AbstractConverter<PrivilegeEntity, PrivilegeDto> {

    @Override
    public PrivilegeEntity toEntity(PrivilegeDto dto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateEntity(PrivilegeEntity entity, PrivilegeDto dto) {
        // TODO Auto-generated method stub

    }

    @Override
    public PrivilegeDto toDto(PrivilegeEntity enity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateDto(PrivilegeDto dto, PrivilegeEntity entity) {
        // TODO Auto-generated method stub

    }
}
