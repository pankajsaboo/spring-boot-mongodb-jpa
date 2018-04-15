package com.templateproject.schema.validation;

import com.fasterxml.jackson.databind.JsonNode;

public interface Parser {

	JsonNode getSchemaNode(String fieldName);

	JsonNode getPropertyNode(String property);

	JsonNode convertObjectToJson(Object instance);

	String getNodeType(JsonNode node);

	String getArrayElementType(JsonNode node);
	
	String getCustomObjectElementType(JsonNode node);

	JsonNode getCustomNode(String nodeName);

}
