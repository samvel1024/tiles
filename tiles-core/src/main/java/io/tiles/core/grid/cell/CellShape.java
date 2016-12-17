package io.tiles.core.grid.cell;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Samvel Abrahamyan 11/14/16.
 */
public enum CellShape {

    UR(Direction.UP, Direction.RIGHT),
    RB(Direction.RIGHT, Direction.BOTTOM),
    BL(Direction.BOTTOM, Direction.LEFT),
    LU(Direction.LEFT, Direction.UP),
    RL(Direction.RIGHT, Direction.LEFT),
    BU(Direction.BOTTOM, Direction.UP);


    private Set<Direction> connections;

    CellShape(Direction... connectingDirections) {
        this.connections = EnumSet.copyOf(Arrays.asList(connectingDirections));
    }

    public boolean connects(Direction dir1, Direction dir2) {
        return this.connections.contains(dir1) && this.connections.contains(dir2);
    }

    public Set<Direction> getConnectionDirections() {
        return new HashSet<>(connections);
    }

    public CellShape rotate() {
        switch (this) {
            case UR:
                return RB;
            case RB:
                return BL;
            case BL:
                return LU;
            case LU:
                return UR;
            case RL:
                return BU;
            case BU:
                return RL;
            default:
                throw new IllegalArgumentException("No rotation for tile shape " + String.valueOf(this));
        }
    }
}
