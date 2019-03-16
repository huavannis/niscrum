package com.hvnis.niscrum.converter;

import org.springframework.stereotype.Component;

import com.hvnis.niscrum.dto.StoryDto;
import com.hvnis.niscrum.entity.StoryEntity;

/**
 * @author hvnis
 */
@Component
public class StoryConverter extends AbstractConverter<StoryEntity, StoryDto> {

	@Override
	public StoryEntity toEntity(StoryDto dto) {
		return new StoryEntity(dto.getName());
	}

	@Override
	public StoryDto toDto(StoryEntity enity) {
		return new StoryDto(enity.getName());
	}

}
