package com.business.intelligence.service.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonUtils {
    private static final ObjectMapper JSON_MAPPER;

    static {
        JSON_MAPPER = new JsonMapper()
                .registerModule(new JavaTimeModule());
    }

    public static ObjectMapper getJsonMapper() {
        return JSON_MAPPER;
    }
}
