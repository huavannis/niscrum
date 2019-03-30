package com.hvnis.niscrum.converter;

import com.hvnis.niscrum.dto.AbstractDto;
import com.hvnis.niscrum.entity.AbstractEntity;

/**
 * @author hvnis
 */
public abstract class AbstractConverter<ENTITY extends AbstractEntity, DTO extends AbstractDto> {

    public abstract ENTITY toEntity(DTO dto);

    public abstract void updateEntity(ENTITY entity, DTO dto);

    public abstract DTO toDto(ENTITY enity);

    public abstract void updateDto(DTO dto, ENTITY entity);
}
