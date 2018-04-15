package com.templateproject.controller;

import static com.templateproject.constants.ErrorCodeConstants.INTERNAL_ERROR;
import static com.templateproject.constants.ErrorCodeConstants.NOT_FOUND;
import static com.templateproject.constants.ErrorCodeConstants.NOT_VALID;
import static com.templateproject.constants.ErrorCodeConstants.SCHEMA_NOT_VALID;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.templateproject.dto.ErrorDto;
import com.templateproject.dto.ValidationErrorDto;
import com.templateproject.exception.NotFoundException;
import com.templateproject.exception.SchemaValidationException;
import com.templateproject.exception.ValidationException;
import com.templateproject.schema.validation.ValidationConstants;
import com.templateproject.schema.validation.ValidationErrorDetail;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(SchemaValidationException.class)
	public ResponseEntity<List<ErrorDto>> handleSchemaValidationException(SchemaValidationException e) {
		final List<ErrorDto> errors = new ArrayList<>();
		for (ValidationErrorDetail validationErrorDetail : e.getValidationErrorDetails()) {
			final String reason;
			switch (validationErrorDetail.getErrorType()) {
				case ValidationConstants.REQUIRED:
					reason = "A value was not provided for the required field.";
					break;
				case ValidationConstants.MAX_LENGTH:
					reason = "The value provided exceeded the maximum field length.";
					break;
				case ValidationConstants.MINIMUM:
					reason = "The value provided was below the minimum allowed.";
					break;
				case ValidationConstants.MAXIMUM:
					reason = "The value provided was above the maximum allowed.";
					break;
				case ValidationConstants.PATTERN:
					reason = "The value provided did not match the allowed pattern.";
					break;
				case ValidationConstants.INVALID:
					reason = "The node provided is not part of the allowed schema.";
					break;
				default:
					reason = "The provided value was not valid for the schema.";
					break;
			}
			errors.add(new ErrorDto(SCHEMA_NOT_VALID, validationErrorDetail.getNode(), reason));
		}
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<List<ErrorDto>> handleNotFoundException(NotFoundException e) {
		final String message = "The provided ID '" + e.getValue() + "' was not found.";
		final List<ErrorDto> errorDto = new ArrayList<>() ;
		errorDto.add(new ErrorDto(NOT_FOUND, e.getField(), message));
		return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<List<ErrorDto>> handleValidationException(ValidationException e) {
		final List<ErrorDto> errors = new ArrayList<>();
		errors.add(new ValidationErrorDto(NOT_VALID, e.getField(), e.getReason(), e.getErrorCode()));
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<List<ErrorDto>> handleInternalErrorException(Exception e) {
		final List<ErrorDto> errorDto = new ArrayList<>();
		errorDto.add(new ErrorDto(INTERNAL_ERROR, "", "Something went wrong"));
		return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
