package io.tiles.core.grid.cell;

/**
 * Created by Samvel Abrahamyan 11/14/16.
 */
public class Cell {

    private Player owner;
    private CellShape shape;

    public Cell(CellShape shape) {
        this.shape = shape;
    }

    public CellShape getShape() {
        return shape;
    }

    public void setShape(CellShape shape) {
        this.shape = shape;
    }

    public void rotateShape() {
        this.shape = shape.rotate();
    }

    public boolean isOwned() {
        return getOwner() != null;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
