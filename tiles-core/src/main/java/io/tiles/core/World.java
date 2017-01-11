package io.tiles.core;

import io.tiles.core.grid.Grid;
import io.tiles.core.grid.cell.Player;
import io.tiles.core.grid.cell.Position;

/**
 * Created by Samvel Abrahamyan 11/13/16.
 */
public interface World {

    Turn makeTurn(Position position, Player byPlayer);

    PlayerAdded addPlayer(Player player);

    void removePlayer(Player player);

    Grid getState();
}
