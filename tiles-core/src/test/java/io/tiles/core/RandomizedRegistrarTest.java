package io.tiles.core;

import io.tiles.core.grid.Grid;
import io.tiles.core.grid.cell.CellShape;
import io.tiles.core.grid.cell.Player;
import io.tiles.core.grid.cell.Position;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static io.tiles.core.grid.cell.CellShape.*;
import static junit.framework.TestCase.fail;

/**
 * Created by Samvel Abrahamyan 11/16/16.
 */
public class RandomizedRegistrarTest {

    private MockedRandom mockedRandom = new MockedRandom();
    private RandomizedRegistrar randomizedRegistrar = new RandomizedRegistrar(
            mockedRandom,
            2,
            new CellShape[][]{
                    {RB, BL},
                    {UR, LU, BL},

            }
    );

    private int[][] gridConfig = {
            {1, 4, 4, 1, 2},
            {5, 1, 2, 0, 5},
            {0, 3, 5, 1, 3},
            {4, 4, 0, 3, 5}
    };


    @Test
    public void testResponse(){
        Grid grid = new MatrixParsedGridFactory(gridConfig).create();
        Player player = new Player(){};

        Set<Position> positionSet = randomizedRegistrar.registerPlayer(grid, player);

        positionSet.forEach(pos -> Assert.assertTrue(grid.getCellAt(pos).getOwner() == player ));
    }

    @Test
    public void testPlayerPlacementAndFullRoom(){
        mockedRandom.mock(Position.of(1, 2));
        Grid grid = new MatrixParsedGridFactory(gridConfig).create();
        Player player = new Player(){};

        Set<Position> positionSet = randomizedRegistrar.registerPlayer(grid, player);

        Map<Position, CellShape> expected = new LinkedHashMap<>();
        expected.put(Position.of(1, 2), RB);
        expected.put(Position.of(1, 3), BL);
        expected.put(Position.of(2, 2), UR);
        expected.put(Position.of(2, 3), LU);
        expected.put(Position.of(2, 4), BL);

        Assert.assertEquals(expected.keySet(), positionSet);
        expected.forEach((pos, shape) -> Assert.assertEquals(grid.getCellAt(pos).getShape(), shape));
    }

    @Test
    public void testNoCellLeft(){
        mockedRandom.mock(Position.of(1, 2));
        Grid grid = new MatrixParsedGridFactory(gridConfig).create();

        randomizedRegistrar.registerPlayer(grid, new Player(){});

        try {
            randomizedRegistrar.registerPlayer(grid, new Player() {});
            fail("Should throw OutOfCellsException");
        }catch (OutOfCellsException e){
            //expected
        }
    }

    private class MockedRandom extends Random{

        private Queue<Position> queuedPositions = new LinkedList<>();
        private boolean rowWasRequested = false;

        public MockedRandom mock(Position position){
            this.queuedPositions.add(position);
            return this;
        }

        @Override
        public int nextInt(int bound) {
            if (queuedPositions.isEmpty())
                return super.nextInt(bound);

            if (!rowWasRequested) {
                rowWasRequested = true;
                return queuedPositions.element().row();
            }
            return queuedPositions.remove().col();
        }
    }

}
