package io.tiles.room.core.simpleworld.impl;

import io.tiles.room.core.TurnResponse;
import io.tiles.room.core.grid.Grid;
import io.tiles.room.core.grid.cell.Cell;
import io.tiles.room.core.grid.cell.Player;
import io.tiles.room.core.grid.cell.Position;
import io.tiles.room.core.simpleworld.TurnConnectionHandler;

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
                    boolean isOwnedByOther = !cell.isOwnedBy(player);
                    if (isOwnedByOther) {
                        cell.setOwner(player);
                    }
                    return isOwnedByOther;
                })
                .collect(Collectors.toSet());
        return new TurnResponse(initial, chownedPositions);
    }
}
