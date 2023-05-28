package client.api;

import org.springframework.stereotype.Component;

@Component
public interface JsonParser<T> {

    T parseJson(String jsonString) throws Exception;

    String toJson(Object object) throws Exception;
}
