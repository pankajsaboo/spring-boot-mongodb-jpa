package com.templateproject.exception;


import java.util.List;

import com.templateproject.schema.validation.ValidationErrorDetail;

public class SchemaValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private List<ValidationErrorDetail> validationErrorDetails;

	public SchemaValidationException(List<ValidationErrorDetail> validationErrorDetails) {
		this.validationErrorDetails = validationErrorDetails;
	}

	public List<ValidationErrorDetail> getValidationErrorDetails() {
		return this.validationErrorDetails;
	}

}
