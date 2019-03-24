package com.hvnis.niscrum.helper;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author hvnis
 */
@Component
public class JsonHelper {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public <T> String parseToJson(T object) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(object);
    }

}
