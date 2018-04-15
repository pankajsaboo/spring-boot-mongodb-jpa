package com.templateproject.schema.validation;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

@FunctionalInterface
public interface Validator {

	List<ValidationErrorDetail> validate(Parser parser, JsonNode instanceJsonNode);

}
