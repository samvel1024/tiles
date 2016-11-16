package io.tiles.core;

import io.tiles.core.grid.cell.Player;
import io.tiles.core.grid.cell.Position;

import java.util.List;

/**
 * Created by Samvel Abrahamyan 11/13/16.
 */
public interface World {

    List<Position> rotateShape(Position position);

    void addPlayer(Player player);

    void removePlayer();

}
