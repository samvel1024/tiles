package com.balitech.tiled.grid.cell;

/**
 * Created by Samvel Abrahamyan 11/14/16.
 */
public class Cell {

    private Player owner;
    private CellShape shape;

    public Cell(CellShape shape){
        this.shape = shape;
    }

    public CellShape getShape() {
        return shape;
    }

    public void rotateShape(){
        this.shape = shape.rotate();
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }
}
