package com.hvnis.niscrum.exception;

import lombok.Value;

/**
 * @author hvnis
 */
@Value
public class ErrorResponse {
	
	private Integer errorCode;
	
	private String errorMessage;
}
