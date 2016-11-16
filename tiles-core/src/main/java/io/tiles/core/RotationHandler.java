package io.tiles.core;

import io.tiles.core.grid.Grid;
import io.tiles.core.grid.cell.Position;

import java.util.List;

/**
 * Created by Samvel Abrahamyan 11/14/16.
 */
public interface RotationHandler {

    List<Position> getConnectedPositions(Grid grid, Position position);

}
