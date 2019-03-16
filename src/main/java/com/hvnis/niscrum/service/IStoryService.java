package com.hvnis.niscrum.service;

import java.util.List;

import com.hvnis.niscrum.dto.StoryDto;

/**
 * @author hvnis
 */
public interface IStoryService {

	public List<StoryDto> findAll();
}
