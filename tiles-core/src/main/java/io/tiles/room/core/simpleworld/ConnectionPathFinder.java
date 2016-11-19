package io.tiles.room.core.simpleworld;

import io.tiles.room.core.grid.Grid;
import io.tiles.room.core.grid.cell.Position;

import java.util.Set;

/**
 * Created by Samvel Abrahamyan 11/14/16.
 */
public interface ConnectionPathFinder {

    Set<Position> getConnectedPositions(Grid grid, Position position);

}
