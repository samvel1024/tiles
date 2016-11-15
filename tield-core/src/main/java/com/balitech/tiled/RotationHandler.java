package com.balitech.tiled;

import com.balitech.tiled.grid.Grid;
import com.balitech.tiled.grid.cell.Position;

import java.util.List;

/**
 * Created by Samvel Abrahamyanon 11/14/16.
 */
public interface RotationHandler {

    List<Position> getConnectedPositions(Grid grid, Position position);

}
