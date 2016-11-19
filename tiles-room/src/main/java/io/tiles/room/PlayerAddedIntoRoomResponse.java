package io.tiles.room;

import io.tiles.room.core.PlayerAddedResponse;
import io.tiles.room.core.grid.cell.Player;
import io.tiles.room.core.grid.cell.Position;

import java.util.Set;

/**
 * Created by Samvel Abrahamyan 11/19/16.
 */
public class PlayerAddedIntoRoomResponse extends PlayerAddedResponse{
    public PlayerAddedIntoRoomResponse(Set<Position> positions, Player player) {
        super(positions, player);
    }

}
