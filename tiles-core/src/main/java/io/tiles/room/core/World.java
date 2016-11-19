package io.tiles.room.core;

import io.tiles.room.core.grid.cell.Player;
import io.tiles.room.core.grid.cell.Position;

/**
 * Created by Samvel Abrahamyan 11/13/16.
 */
public interface World {

    TurnResponse turn(Position position, Player byPlayer);

    PlayerAddedResponse addPlayer(Player player);

    void removePlayer();

}
