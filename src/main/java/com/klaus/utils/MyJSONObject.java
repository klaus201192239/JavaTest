package com.klaus.utils;

import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class MyJSONObject {
	
	private ObjectMapper mapper;
	private ObjectNode root;

	public ObjectMapper getMapper() {
		return mapper;
	}

	public void setMapper(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	public MyJSONObject() {
		
		mapper=new ObjectMapper();
		
	}
	
	public MyJSONObject(String json) {
		mapper=new ObjectMapper();
		
	}
	
	public MyJSONObject(Map<?, ?> map) {	
		mapper=new ObjectMapper();
		
	}
	
	public MyJSONObject(Object bean) throws Exception {
		
		mapper=new ObjectMapper();
		JsonNode node = mapper.readTree(mapper.writeValueAsString(bean));  
		
		
		
		root=mapper.createObjectNode();
		
		
		
	}
	
	
	
}
