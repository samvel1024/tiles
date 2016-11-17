package io.tiles.core.simpleworld;

import io.tiles.core.TurnResponse;
import io.tiles.core.grid.Grid;
import io.tiles.core.grid.cell.Player;
import io.tiles.core.grid.cell.Position;

import java.util.Set;

/**
 * Created by Samvel Abrahamyan 11/16/16.
 */
public interface TurnConnectionHandler {

    TurnResponse handleConnectionPath(Grid grid, Set<Position> connections, Player player, Position initialPosition);

}
