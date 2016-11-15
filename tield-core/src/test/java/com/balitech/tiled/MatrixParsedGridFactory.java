package com.balitech.tiled;

import com.balitech.tiled.grid.Grid;
import com.balitech.tiled.grid.GridFactory;
import com.balitech.tiled.grid.cell.Cell;
import com.balitech.tiled.grid.cell.CellShape;

/**
 * Created by Samvel Abrahamyanon 11/15/16.
 */
public class MatrixParsedGridFactory implements GridFactory{

    private int [][] config;

    public MatrixParsedGridFactory(int [][] config){
        this.config = config;
    }

    @Override
    public Grid create() {

        Cell [][] cells = new Cell[config.length][config[0].length];
        CellShape[] shapes = CellShape.values();
        for (int i=0; i<config.length; ++i){
            for (int j=0; j<config[0].length; ++j){
                cells[i][j] = new Cell(shapes[config[i][j]]);
            }
        }
        return new Grid(cells);
    }




}
