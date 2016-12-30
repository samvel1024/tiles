package io.tiles.core;

import io.tiles.core.grid.cell.Player;
import io.tiles.core.grid.cell.Position;

import java.util.Set;

/**
 * Created by Samvel Abrahamyan 11/16/16.
 */
public class PlayerAdded {

    private Set<Position> positions;
    private Player player;

    public PlayerAdded(Set<Position> positions, Player player) {
        this.positions = positions;
        this.player = player;
    }

    public Set<Position> getPositions() {
        return positions;
    }

    public Player getPlayer() {
        return player;
    }
}
