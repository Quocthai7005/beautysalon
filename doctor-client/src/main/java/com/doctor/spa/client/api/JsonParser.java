package com.doctor.spa.client.api;

public interface JsonParser<T> {

    T parseJson(String jsonString) throws Exception;

    String toJson(Object object) throws Exception;
}
