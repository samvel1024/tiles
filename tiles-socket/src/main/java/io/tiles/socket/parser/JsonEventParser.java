package io.tiles.socket.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * Created by Samvel Abrahamyan 12/16/16.
 */
public class JsonEventParser<E> extends EventParser<E> {

    private final ObjectMapper objectMapper;
    private final Class<E> klass;

    public JsonEventParser(String key, @Autowired ObjectMapper objectMapper, Class<E> klass) {
        super(key);
        this.objectMapper = objectMapper;
        this.klass = klass;
    }

    @Override
    public E parse(String event) {
        try {
            return objectMapper.readValue(event, klass);
        } catch (IOException e) {
            throw new IllegalStateException("Error parsing event", e);
        }
    }

}
