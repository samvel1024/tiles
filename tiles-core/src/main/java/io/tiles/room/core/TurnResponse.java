package io.tiles.room.core;

import io.tiles.room.core.grid.cell.Position;

import java.util.Set;

/**
 * Created by Samvel Abrahamyan 11/16/16.
 */
public class TurnResponse {

    private Position rotatedPosition;

    private Set<Position> chownedPositions;

    public TurnResponse(Position rotatedPosition, Set<Position> chownedPositions) {
        this.rotatedPosition = rotatedPosition;
        this.chownedPositions = chownedPositions;
    }

    public Position getRotatedPosition() {
        return rotatedPosition;
    }

    public Set<Position> getChownedPostions() {
        return chownedPositions;
    }
}
