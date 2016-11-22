package io.tiles.roommanager;

import io.tiles.room.Room;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Samvel Abrahamyan 11/21/16.
 */
public class RoomManager {

    private Map<RoomKey, Room> rooms = new ConcurrentHashMap<>();

    



}
