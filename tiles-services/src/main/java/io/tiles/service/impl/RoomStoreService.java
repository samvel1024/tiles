package io.tiles.service.impl;

import io.tiles.room.Room;

/**
 * Created by Samvel Abrahamyan 11/27/16.
 */
public interface RoomStoreService {

    Long putRoom(Room size);

    Room getRoom(Long id);

}
