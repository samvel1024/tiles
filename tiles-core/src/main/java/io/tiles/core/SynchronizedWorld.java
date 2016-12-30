package io.tiles.core;

import io.tiles.core.grid.cell.Player;
import io.tiles.core.grid.cell.Position;

/**
 * Created by Samvel Abrahamyan 11/19/16.
 */
public class SynchronizedWorld implements World {

    private World world;
    private final Object mutex = new Object();

    public SynchronizedWorld(World world) {
        this.world = world;
    }

    @Override
    public Turn makeTurn(Position position, Player byPlayer) {
        synchronized (mutex) {
            return world.makeTurn(position, byPlayer);
        }
    }

    @Override
    public PlayerAdded addPlayer(Player player) {
        synchronized (mutex) {
            return world.addPlayer(player);
        }
    }

    @Override
    public void removePlayer(Player pl) {
        throw new UnsupportedOperationException("Not supported");
    }

}
