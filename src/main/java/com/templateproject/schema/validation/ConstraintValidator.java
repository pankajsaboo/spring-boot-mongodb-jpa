package com.templateproject.schema.validation;

import com.fasterxml.jackson.databind.JsonNode;
import com.transferservice.schema.validation.type.ValidatorFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class ConstraintValidator implements Validator {

	@Override
	public List<ValidationErrorDetail> validate(Parser parser, JsonNode instanceJsonNode) {
		final List<ValidationErrorDetail> validationErrorDetails = new ArrayList<>();
		for (Iterator<Entry<String, JsonNode>> instanceIter = instanceJsonNode.fields(); instanceIter.hasNext(); ) {
			Entry<String, JsonNode> instanceNode = instanceIter.next();
			JsonNode node = parser.getPropertyNode(instanceNode.getKey());
			if (node == null) {
				validationErrorDetails.add(new ValidationErrorDetail(instanceNode.getKey(), ValidationConstants.INVALID));
				continue;
			}
			validationErrorDetails.addAll(ValidatorFactory.getValidator(parser, parser.getNodeType(node)).validate(instanceNode, node));
		}
		return validationErrorDetails;
	}
}
