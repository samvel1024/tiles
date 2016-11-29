package io.tiles.core.grid.cell;

/**
 * Created by Samvel Abrahamyan 11/14/16.
 */
public enum Direction {
    UP(Position.of(-1, 0)),
    RIGHT(Position.of(0, 1)),
    BOTTOM(Position.of(1, 0)),
    LEFT(Position.of(0, -1));

    private Position movementDirection;

    Direction(Position unitMovement) {
        this.movementDirection = unitMovement;
    }

    public Position unitMovement() {
        return movementDirection;
    }

    public Direction opposite() {
        switch (this) {
            case UP:
                return BOTTOM;
            case BOTTOM:
                return UP;
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
            default:
                throw new IllegalStateException("Uncovered direction case");
        }
    }


}
