package io.tiles.core.simpleworld.impl;

import io.tiles.core.grid.Grid;
import io.tiles.core.grid.GridFactory;
import io.tiles.core.grid.cell.Cell;
import io.tiles.core.grid.cell.CellShape;
import io.tiles.core.grid.cell.Position;

/**
 * Created by Samvel Abrahamyan 11/15/16.
 */
public class MatrixParsedGridFactory implements GridFactory {

    private int[][] config;

    public MatrixParsedGridFactory(int[][] config) {
        this.config = config;
    }

    public Grid create() {

        Cell[][] cells = new Cell[config.length][config[0].length];
        CellShape[] shapes = CellShape.values();
        for (int i = 0; i < config.length; ++i) {
            for (int j = 0; j < config[0].length; ++j) {
                cells[i][j] = new Cell(shapes[config[i][j]]);
            }
        }
        return new Grid(cells);
    }


    @Override
    public Grid create(Position size) {
        return null;
    }
}
