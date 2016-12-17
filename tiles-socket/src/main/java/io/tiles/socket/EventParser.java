package io.tiles.socket;

/**
 * Created by Samvel Abrahamyan 12/16/16.
 */
public abstract class EventParser<E> {

    private String key;

    public EventParser(String key){
        this.key = key;
    }

    abstract E parse(String event);

    public String getKey(){
        return key;
    }

}
