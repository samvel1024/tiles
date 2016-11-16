package io.tiles.core;

import io.tiles.core.grid.Grid;
import io.tiles.core.grid.cell.Player;

/**
 * Created by Samvel Abrahamyan 11/16/16.
 */
public interface GridPlayerRegistrar {

    void registerPlayer(Grid grid, Player player);
    void unregisterPlayer(Grid grid, Player player);

}
