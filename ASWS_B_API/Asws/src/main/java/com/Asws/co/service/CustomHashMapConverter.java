package com.Asws.co.service;

import java.io.IOException;
import java.util.Map;

import javax.persistence.AttributeConverter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomHashMapConverter implements AttributeConverter<Map<String, Object>, String>{
    

    private static final Logger logger = LoggerFactory.getLogger(CustomHashMapConverter.class);
	ObjectMapper objectMapper = new ObjectMapper();
	
    @Override
    public String convertToDatabaseColumn(Map<String, Object> map) {

        String strJson = null;
        try {
        	strJson = objectMapper.writeValueAsString(map);
        } catch (final JsonProcessingException e) {
            logger.error("JSON writing error", e);
        }

        return strJson;
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String strJson) {
    	Map<String, Object> map = null;
    	if(StringUtils.isBlank(strJson)) {
    		return map;
    	}
        try {
            map = objectMapper.readValue(strJson, Map.class);
        } catch (final IOException e) {
            logger.error("JSON reading error", e);
        }

        return map;
    }
}
