package io.tiles.core;

import io.tiles.core.grid.Grid;
import io.tiles.core.grid.cell.Player;
import io.tiles.core.grid.cell.Position;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Samvel Abrahamyan 11/16/16.
 */
public class SimpleWorld implements World {

    private Grid grid;
    private ConnectionPathFinder connectionPathFinder;
    private GridPlayerRegistrar gridPlayerRegistrar;
    private Set<Player> players = new HashSet<>();

    public SimpleWorld(Grid grid, ConnectionPathFinder handler, GridPlayerRegistrar gridPlayerRegistrar){
        this.grid = grid;
        this.connectionPathFinder = handler;
        this.gridPlayerRegistrar = gridPlayerRegistrar;
    }

    @Override
    public List<Position> rotateShape(Position position) {
        return null;
    }

    @Override
    public void addPlayer(Player player){
        this.players.add(player);
    }

    @Override
    public void removePlayer() {
        throw new UnsupportedOperationException("Not implemented");
    }

}
