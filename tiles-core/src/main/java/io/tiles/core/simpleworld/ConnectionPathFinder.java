package io.tiles.core.simpleworld;

import io.tiles.core.grid.Grid;
import io.tiles.core.grid.cell.Position;

import java.util.Set;

/**
 * Created by Samvel Abrahamyan 11/14/16.
 */
public interface ConnectionPathFinder {

    Set<Position> getConnectedPositions(Grid grid, Position position);

}
