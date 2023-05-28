package client.impl;

import client.api.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParserImpl<T> implements JsonParser {
    private final ObjectMapper objectMapper;
    private final Class<T> objectType;

    public JsonParserImpl(Class<T> objectType) {
        this.objectMapper = new ObjectMapper();
        this.objectType = objectType;
    }

    @Override
    public T parseJson(String jsonString) throws Exception {
        return objectMapper.readValue(jsonString, objectType);
    }

    @Override
    public String toJson(Object object) throws Exception {
        return objectMapper.writeValueAsString(object);
    }
}