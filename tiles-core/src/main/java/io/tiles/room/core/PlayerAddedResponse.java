package io.tiles.room.core;

import io.tiles.room.core.grid.cell.Player;
import io.tiles.room.core.grid.cell.Position;

import java.util.Set;

/**
 * Created by Samvel Abrahamyan 11/16/16.
 */
public class PlayerAddedResponse {

    private Set<Position> positions;
    private Player player;

    public PlayerAddedResponse(Set<Position> positions, Player player) {
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
