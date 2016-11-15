package com.balitech.tiled;

import com.balitech.tiled.grid.cell.Position;

import java.util.List;

/**
 * Created by Samvel Abrahamyanon 11/14/16.
 */
public class RotationResponse {

    private Position rotated;
    private List<Position> chownedPositions;

    public RotationResponse(Position rotated, List<Position> chownedPositions) {
        this.rotated = rotated;
        this.chownedPositions = chownedPositions;
    }

    public Position getRotated() {
        return rotated;
    }

    public List<Position> getChownedPositions() {
        return chownedPositions;
    }
}
