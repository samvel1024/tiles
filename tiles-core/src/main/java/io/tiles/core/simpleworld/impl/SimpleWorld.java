package io.tiles.core.simpleworld.impl;

import io.tiles.core.PlayerAdded;
import io.tiles.core.Turn;
import io.tiles.core.UnauthorizedTurnException;
import io.tiles.core.World;
import io.tiles.core.grid.Grid;
import io.tiles.core.grid.cell.Player;
import io.tiles.core.grid.cell.Position;
import io.tiles.core.simpleworld.ConnectionPathFinder;
import io.tiles.core.simpleworld.GridPlayerRegistrar;
import io.tiles.core.simpleworld.TurnConnectionHandler;

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
    public Turn makeTurn(Position position, Player player) {
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
    public PlayerAdded addPlayer(Player player) {
        return new PlayerAdded(
                gridPlayerRegistrar.registerPlayer(grid, player),
                player
        );
    }

    @Override
    public void removePlayer(Player pl) {
        throw new UnsupportedOperationException("Not implemented");
    }

    private boolean isPositionOwnedByPlayer(Position position, Player player) {
        return grid.getCellAt(position).isOwnedBy(player);
    }

}
