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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TurnResponse that = (TurnResponse) o;

        if (rotatedPosition != null ? !rotatedPosition.equals(that.rotatedPosition) : that.rotatedPosition != null)
            return false;
        return chownedPositions != null ? chownedPositions.equals(that.chownedPositions) : that.chownedPositions == null;

    }

    @Override
    public int hashCode() {
        int result = rotatedPosition != null ? rotatedPosition.hashCode() : 0;
        result = 31 * result + (chownedPositions != null ? chownedPositions.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TurnResponse{" +
                "rotatedPosition=" + rotatedPosition +
                ", chownedPositions=" + chownedPositions +
                '}';
    }
}
