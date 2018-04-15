package com.templateproject.schema.validation;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

public class SchemaValidator {

	public boolean isValid(Parser parser, Object instance) {
		return getErrorDetails(parser, instance).isEmpty();
	}

	public List<ValidationErrorDetail> getErrorDetails(Parser parser, Object instance) {
		final List<ValidationErrorDetail> validationErrorDetails = new ArrayList<>();
		final JsonNode instanceJsonNode = parser.convertObjectToJson(instance);

		validationErrorDetails.addAll(new RequiredValidator().validate(parser, instanceJsonNode));
		validationErrorDetails.addAll(new ConstraintValidator().validate(parser, instanceJsonNode));

		return validationErrorDetails;
	}

}
