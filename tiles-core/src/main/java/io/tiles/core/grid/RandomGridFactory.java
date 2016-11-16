package io.tiles.core.grid;

import io.tiles.core.grid.cell.Cell;
import io.tiles.core.grid.cell.CellShape;
import io.tiles.core.grid.cell.Position;

import java.util.Random;

/**
 * Created by Samvel Abrahamyan 11/14/16.
 */
public class RandomGridFactory implements GridFactory{

    private Position size;
    private Random random;
    private CellShape shapes[];


    public RandomGridFactory(Position size){
        this.size = size;
        this.random = new Random();
        this.shapes = CellShape.values();
    }

    public Grid create(){
        Cell[][] cells = new Cell[size.row()][size.col()];
        for (int r=0; r<size.row(); ++r){
            for (int c=0; c<size.col(); ++c){
                cells[r][c] = new Cell(shapes[random.nextInt(shapes.length)]);
            }
        }
        return new Grid(cells);
    }

}
