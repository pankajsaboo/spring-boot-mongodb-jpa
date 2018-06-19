package com.transferservice.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.templateproject.exception.SchemaValidationException;
import com.templateproject.schema.validation.SchemaParser;
import com.templateproject.schema.validation.SchemaValidator;
import com.templateproject.schema.validation.ValidationErrorDetail;

/**
 * 
 * Validation service is for doing validations.
 *
 */
@Service
public class ValidationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ValidationService.class);
	
	private SchemaValidator schemaValidator;
	private Map<Class<?>, SchemaParser> schemaParserMap = new HashMap<>();
	
	
	public ValidationService() throws IOException {
		schemaValidator = new SchemaValidator();
	}


	/**
	 * 
	 * @param schemaParser
	 * @param dto
	 * @exception SchemaValidationException On invalid input.
	 */
	private void validateSchema(SchemaParser schemaParser, Object dto) {
		LOGGER.debug("validateSchema called with DTO: " + dto);
		final List<ValidationErrorDetail> validationErrorDetails = schemaValidator.getErrorDetails(schemaParser, dto);
		if (!validationErrorDetails.isEmpty()) {
			LOGGER.error("The provided DTO did not match the schema, and returned errors: " + validationErrorDetails);
			throw new SchemaValidationException(validationErrorDetails);
		}
	}
}
