package io.tiles.service;

import io.tiles.core.grid.cell.Position;
import io.tiles.room.Room;

/**
 * Created by Samvel Abrahamyan 11/27/16.
 */
public interface RoomStoreService {

    Long putRoom(Position size);

    Room getRoom(Long Long);

}
