package io.tiles.core.grid.cell;

/**
 * Created by Samvel Abrahamyan 11/14/16.
 */
public class Position {

    private int row;
    private int col;

    public Position(int row, int col){
        this.row = row;
        this.col = col;
    }

    public static Position of(int row, int col){
        return new Position(row, col);
    }


    public int row() {
        return row;
    }
    
    public boolean isInside(Position size){
        return this.row >= 0 && this.row < size.row && this.col >= 0 && this.col < size.col;
    }

    public int col() {
        return col;
    }

    public Position movedBy(Position pos){
        return new Position(this.row + pos.row, this.col + pos.col);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (row != position.row) return false;
        return col == position.col;

    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + col;
        return result;
    }

    @Override
    public String toString() {
        return "Position{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }
}
