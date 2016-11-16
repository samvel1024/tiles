package io.tiles.core.grid;

import io.tiles.core.grid.cell.Cell;
import io.tiles.core.grid.cell.Position;

import java.util.Arrays;

/**
 * Created by Samvel Abrahamyan 11/14/16.
 */
public class Grid {

    private Cell[][] cells;
    private Position size;

    public Grid(Cell[][] cells){
        this.cells = cells;
        this.size = Position.of(cells.length, cells[0].length);
    }

    public Position getSize() {
        return size;
    }

    public Cell getCellAt(Position pos){
        return this.cells[pos.row()][pos.col()];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Grid grid = (Grid) o;

        if (!Arrays.deepEquals(cells, grid.cells)) return false;
        return size != null ? size.equals(grid.size) : grid.size == null;

    }

    @Override
    public int hashCode() {
        int result = Arrays.deepHashCode(cells);
        result = 31 * result + (size != null ? size.hashCode() : 0);
        return result;
    }
}
