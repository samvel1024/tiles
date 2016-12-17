package io.tiles.core;

import io.tiles.core.grid.cell.Position;

import java.util.List;

/**
 * Created by Samvel Abrahamyan 11/16/16.
 */
public class TurnResponse {

    private Position rotatedPosition;

    private List<Position> chownedPositions;

    public TurnResponse(Position rotatedPosition, List<Position> chownedPositions) {
        this.rotatedPosition = rotatedPosition;
        this.chownedPositions = chownedPositions;
    }

    public Position getRotatedPosition() {
        return rotatedPosition;
    }

    public List<Position> getChownedPostions() {
        return chownedPositions;
    }
}
