package io.tiles.core.grid;

import io.tiles.core.grid.cell.Cell;
import io.tiles.core.grid.cell.Position;

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

}
