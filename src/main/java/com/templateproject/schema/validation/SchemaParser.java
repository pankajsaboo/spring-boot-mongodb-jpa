package com.templateproject.schema.validation;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.templateproject.schema.validation.Parser;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class SchemaParser implements Parser {

	private JsonNode schema;

	public SchemaParser(String schemaString) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		schema = mapper.readTree(schemaString);
	}

	@Override
	public JsonNode convertObjectToJson(Object instance) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_EMPTY);
		return mapper.convertValue(instance, JsonNode.class);
	}

	@Override
	public JsonNode getPropertyNode(String property) {
		return schema.findValue("properties").findValue(property);
	}

	@Override
	public JsonNode getCustomNode(String nodeName) {
		final JsonNode definitionsNode = schema.findValue("definitions");
		for(Iterator<Map.Entry<String, JsonNode>> iterator = definitionsNode.fields(); iterator.hasNext(); ) {
			Map.Entry<String, JsonNode> node = iterator.next();
			if(nodeName.equals(node.getKey())) {
				return node.getValue();
			}
		}
		return null;
	}

	@Override
	public String getNodeType(JsonNode node) {
		JsonNode nodeType = node.findValue("type");
		return (nodeType == null) ?
				node.findValue("$ref").asText().replaceAll("#/definitions/", "") :
				(nodeType.isTextual() ? nodeType.asText() : nodeType.fields().next().getKey());
	}

	@Override
	public String getArrayElementType(JsonNode node) {
		return getNodeType(node.findValue("items"));
	}

	@Override
	public JsonNode getSchemaNode(String fieldName) {
		return schema.findValue(fieldName);
	}

	@Override
	public String getCustomObjectElementType(JsonNode node) {
		JsonNode valueNode = node.findValue("value");
		return (valueNode == null) ? null : getNodeType(valueNode);
	}
}
