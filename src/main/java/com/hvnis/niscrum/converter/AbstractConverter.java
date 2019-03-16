package com.hvnis.niscrum.converter;

import com.hvnis.niscrum.dto.AbstractDto;
import com.hvnis.niscrum.entity.AbstractEntity;

/**
 * @author hvnis
 */
public abstract class AbstractConverter<ENTITY extends AbstractEntity, DTO extends AbstractDto> {

	public abstract ENTITY toEntity(DTO dto);
	
	public abstract DTO toDto(ENTITY enity);
}
