package io.tiles.room.core.simpleworld.impl;

import io.tiles.room.core.PlayerAddedResponse;
import io.tiles.room.core.TurnResponse;
import io.tiles.room.core.UnauthorizedTurnException;
import io.tiles.room.core.World;
import io.tiles.room.core.grid.Grid;
import io.tiles.room.core.grid.cell.Player;
import io.tiles.room.core.grid.cell.Position;
import io.tiles.room.core.simpleworld.ConnectionPathFinder;
import io.tiles.room.core.simpleworld.GridPlayerRegistrar;
import io.tiles.room.core.simpleworld.TurnConnectionHandler;

/**
 * Created by Samvel Abrahamyan 11/16/16.
 */
public class SimpleWorld implements World {

    private Grid grid;
    private ConnectionPathFinder connectionPathFinder;
    private GridPlayerRegistrar gridPlayerRegistrar;
    private TurnConnectionHandler connectionHandler;


    public SimpleWorld(
            Grid grid,
            ConnectionPathFinder handler,
            GridPlayerRegistrar gridPlayerRegistrar,
            TurnConnectionHandler connectionHandler
    ) {
        this.grid = grid;
        this.connectionPathFinder = handler;
        this.gridPlayerRegistrar = gridPlayerRegistrar;
        this.connectionHandler = connectionHandler;
    }

    @Override
    public TurnResponse turn(Position position, Player player) {
        if (!this.isPositionOwnedByPlayer(position, player)) {
            throw new UnauthorizedTurnException();
        }
        grid.getCellAt(position).rotateShape();
        return connectionHandler.handleConnectionPath(
                grid,
                connectionPathFinder.getConnectedPositions(grid, position),
                player,
                position
        );
    }

    @Override
    public PlayerAddedResponse addPlayer(Player player) {
        return new PlayerAddedResponse(
                gridPlayerRegistrar.registerPlayer(grid, player),
                player
        );
    }

    @Override
    public void removePlayer() {
        throw new UnsupportedOperationException("Not implemented");
    }

    private boolean isPositionOwnedByPlayer(Position position, Player player) {
        return grid.getCellAt(position).isOwnedBy(player);
    }

}
