package com.hvnis.niscrum.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hvnis.niscrum.converter.StoryConverter;
import com.hvnis.niscrum.dto.StoryDto;
import com.hvnis.niscrum.repository.StoryRepository;
import com.hvnis.niscrum.service.IStoryService;

import lombok.AllArgsConstructor;

/**
 * @author hvnis
 */
@Service
@AllArgsConstructor
public class StoryServiceImpl implements IStoryService {

	private final StoryRepository storyRepository;

	private final StoryConverter storyConverter;

	@Override
	public List<StoryDto> findAll() {
		return storyRepository.findAll()
				.stream()
				.map(storyConverter::toDto)
				.collect(Collectors.toList());
	}

}
