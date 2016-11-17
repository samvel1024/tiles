package io.tiles.core.simpleworld.impl;

import io.tiles.core.TurnResponse;
import io.tiles.core.grid.Grid;
import io.tiles.core.grid.cell.Cell;
import io.tiles.core.grid.cell.Player;
import io.tiles.core.grid.cell.Position;
import io.tiles.core.simpleworld.TurnConnectionHandler;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Samvel Abrahamyan 11/16/16.
 */
public class SimpleConnectionHandler implements TurnConnectionHandler {
    @Override
    public TurnResponse handleConnectionPath(Grid grid, Set<Position> connections, Player player, Position initial) {
        Set<Position> chownedPositions = connections.stream()
                .filter(pos -> {
                    Cell cell = grid.getCellAt(pos);
                    boolean isOwnedByOther = cell.getOwner() != player;
                    if (isOwnedByOther) {
                        cell.setOwner(player);
                    }
                    return isOwnedByOther;
                })
                .collect(Collectors.toSet());
        return new TurnResponse(initial, chownedPositions);
    }
}
