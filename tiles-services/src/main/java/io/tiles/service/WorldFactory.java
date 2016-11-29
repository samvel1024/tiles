package io.tiles.service;

import io.tiles.core.World;
import io.tiles.core.grid.cell.Position;

/**
 * Created by Samvel Abrahamyan 11/27/16.
 */
public interface WorldFactory {
    World create(Position size);
}
