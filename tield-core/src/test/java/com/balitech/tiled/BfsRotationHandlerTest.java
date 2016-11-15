package com.balitech.tiled;

import com.balitech.tiled.grid.Grid;
import com.balitech.tiled.grid.cell.Position;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.balitech.tiled.grid.cell.Position.of;

/**
 * Created by Samvel Abrahamyanon 11/14/16.
 */
public class BfsRotationHandlerTest {


    private BfsRotationHandler handler = new BfsRotationHandler();
    private Grid grid = new MatrixParsedGridFactory(new int[][]{
            {1, 4, 4, 1, 2},
            {5, 1, 2, 0, 5},
            {0, 3, 5, 1, 3},
            {4, 4, 0, 3, 5}
    }).create();
    private Set<Position> expected = new HashSet<>(Arrays.asList(
            of(1, 3),
            of(0, 3),
            of(0, 4),
            of(1, 4),
            of(2, 4),
            of(2, 3),
            of(3, 3),
            of(3, 2),
            of(2, 2),
            of(1, 1),
            of(2, 1),
            of(2, 0),
            of(1, 0),
            of(0, 0),
            of(0, 1),
            of(0, 2)
    ));

    @Test
    public void longestConnectionTest() throws IOException {
        verifyLongestConnection(of(0, 2));
        verifyLongestConnection(of(0, 3));
        verifyLongestConnection(of(1, 0));
        verifyLongestConnection(of(0, 2));
    }

    private void verifyLongestConnection(Position starting) {
        Set<Position> positions = new HashSet<>(handler.getConnectedPositions(grid, starting));
        Assert.assertEquals(expected, positions);
    }

    @Test
    public void singleConnectionTest(){
        List<Position> positions = handler.getConnectedPositions(grid, of(3, 4));
        Assert.assertEquals(1, positions.size());
        Assert.assertEquals(of(3, 4), positions.get(0));
    }


}
