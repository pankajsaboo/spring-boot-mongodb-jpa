package com.templateproject.schema.validation;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RequiredValidator implements Validator {

	@Override
	public List<ValidationErrorDetail> validate(Parser parser, JsonNode instanceNode) {
		return validate(parser.getSchemaNode(ValidationConstants.REQUIRED), instanceNode);
	}

	public List<ValidationErrorDetail> validate(JsonNode requiredSchemaNode, JsonNode instanceNode) {
		final List<ValidationErrorDetail> validationErrorDetails = new ArrayList<>();
		for (Iterator<JsonNode> nodeIterator = requiredSchemaNode.elements(); nodeIterator.hasNext(); ) {
			final String requiredNode = nodeIterator.next().asText();
			if (instanceNode.findValue(requiredNode) == null) {
				validationErrorDetails.add(new ValidationErrorDetail(requiredNode, ValidationConstants.REQUIRED));
			}
		}
		return validationErrorDetails;
	}

}
