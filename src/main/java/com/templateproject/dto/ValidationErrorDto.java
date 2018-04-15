package com.templateproject.dto;

import lombok.Getter;

@Getter
public class ValidationErrorDto extends ErrorDto {
	
	private final String errorCode;

	public ValidationErrorDto(String code, String field, String message, String errorCode){
		this.code = code;
		this.field = field;
		this.message = message;
		this.errorCode = errorCode;
	}
}
