package io.tiles.core.grid;

import io.tiles.core.grid.cell.Position;

/**
 * Created by Samvel Abrahamyan 11/14/16.
 */
public interface GridFactory {
    Grid create(Position size);
}
