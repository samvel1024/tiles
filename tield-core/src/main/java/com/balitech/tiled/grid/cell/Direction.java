package com.balitech.tiled.grid.cell;

import static com.balitech.tiled.grid.cell.Position.*;

/**
 * Created by Samvel Abrahamyan 11/14/16.
 */
public enum Direction {
    UP      (of(-1,  0)),
    RIGHT   (of( 0,  1)),
    BOTTOM  (of( 1,  0)),
    LEFT    (of( 0, -1));

    private Position movementDirection;

    Direction(Position unitMovement){
        this.movementDirection = unitMovement;
    }

    public Position unitMovement(){
        return movementDirection;
    }

    public Direction opposite(){
        switch (this){
            case UP: return BOTTOM;
            case BOTTOM: return UP;
            case LEFT: return RIGHT;
            case RIGHT: return LEFT;
            default:
                throw new IllegalStateException("Uncovered direction case");
        }
    }


}
