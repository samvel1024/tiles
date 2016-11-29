package io.tiles.core.simpleworld;

import io.tiles.core.grid.Grid;
import io.tiles.core.grid.cell.Player;
import io.tiles.core.grid.cell.Position;

import java.util.Set;

/**
 * Created by Samvel Abrahamyan 11/16/16.
 */
public interface GridPlayerRegistrar {

    Set<Position> registerPlayer(Grid grid, Player player);

    void unregisterPlayer(Grid grid, Player player);

}
