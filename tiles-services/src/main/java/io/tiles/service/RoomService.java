package io.tiles.service;

import io.tiles.core.TurnResponse;
import io.tiles.core.grid.cell.Player;
import io.tiles.core.grid.cell.Position;
import io.tiles.room.PlayerAddedIntoRoomResponse;

/**
 * Created by Samvel Abrahamyan 11/22/16.
 */
public interface RoomService {

    void createRoom(Long Long);

    PlayerAddedIntoRoomResponse addPlayer(Long key, Player player);

    TurnResponse turn(Long key, Position position, Player player);

}
