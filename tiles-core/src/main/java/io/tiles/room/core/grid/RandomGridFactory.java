package io.tiles.room.core.grid;

import io.tiles.room.core.grid.cell.Cell;
import io.tiles.room.core.grid.cell.CellShape;
import io.tiles.room.core.grid.cell.Position;

import java.util.Random;

/**
 * Created by Samvel Abrahamyan 11/14/16.
 */
public class RandomGridFactory implements GridFactory {

    private Position size;
    private Random random;

    public RandomGridFactory(Position size, Random random) {
        this.size = size;
        this.random = random;
    }

    public Grid create() {
        CellShape[] shapes = CellShape.values();
        Cell[][] cells = new Cell[size.row()][size.col()];
        for (int r = 0; r < size.row(); ++r) {
            for (int c = 0; c < size.col(); ++c) {
                cells[r][c] = new Cell(shapes[random.nextInt(shapes.length)]);
            }
        }
        return new Grid(cells);
    }

}
