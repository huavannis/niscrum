package com.hvnis.niscrum.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hvnis.niscrum.dto.StoryDto;
import com.hvnis.niscrum.exception.ErrorResponse;
import com.hvnis.niscrum.service.IStoryService;

import lombok.AllArgsConstructor;

/**
 * @author hvnis
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/stories")
public class StoryController {

	private final IStoryService storyService;
	
	@RequestMapping("/")
	@ResponseBody
	public List<StoryDto> findAll()
	{
		return storyService.findAll();
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handleInvalidTopTalentDataException(MethodArgumentNotValidException methodArgumentNotValidException) {
		return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), methodArgumentNotValidException.getMessage());
	}
}
