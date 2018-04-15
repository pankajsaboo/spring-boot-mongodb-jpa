package com.templateproject.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ValidationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	private String field;

	@Getter
	private String reason;
	
	@Getter
	private String errorCode;
	
}
