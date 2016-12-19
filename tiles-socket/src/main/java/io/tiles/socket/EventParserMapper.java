package io.tiles.socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Samvel Abrahamyan 12/16/16.
 */
@Component
public class EventParserMapper {

    private Map<String, EventParser<?>> eventHandlers;

    public EventParserMapper(@Autowired List<EventParser<?>> eventParsers) {
        eventHandlers = new HashMap<>();
        eventParsers.forEach(parser -> {
            String key = parser.getKey();
            if (eventHandlers.containsKey(key)) {
                String errMessage = String.format(
                        "%s and %s parsers have the same event key",
                        eventHandlers.get(key).getClass().getName(),
                        parser.getClass().getName());
                throw new IllegalStateException(errMessage);
            }
            eventHandlers.put(parser.getKey(), parser);
        });
    }

    public EventParser<?> getParserByKey(String key){
        return eventHandlers.get(key);
    }


}
