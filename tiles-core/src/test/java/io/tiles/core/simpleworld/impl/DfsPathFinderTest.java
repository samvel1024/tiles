package io.tiles.core.simpleworld.impl;

import io.tiles.core.grid.Grid;
import io.tiles.core.grid.cell.Position;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

/**
 * Created by Samvel Abrahamyan 11/14/16.
 */
public class DfsPathFinderTest {

    private DfsPathFinder handler = new DfsPathFinder();
    private Grid grid = new MatrixParsedGridFactory(new int[][]{
            {1, 4, 4, 1, 2},
            {5, 1, 2, 0, 5},
            {0, 3, 5, 1, 3},
            {4, 4, 0, 3, 5}
    }).create();
    private Set<Position> expected = new HashSet<>(Arrays.asList(
            Position.of(1, 3),
            Position.of(0, 3),
            Position.of(0, 4),
            Position.of(1, 4),
            Position.of(2, 4),
            Position.of(2, 3),
            Position.of(3, 3),
            Position.of(3, 2),
            Position.of(2, 2),
            Position.of(1, 2),
            Position.of(1, 1),
            Position.of(2, 1),
            Position.of(2, 0),
            Position.of(1, 0),
            Position.of(0, 0),
            Position.of(0, 1),
            Position.of(0, 2)
    ));

    @Test
    public void testLongestConnection() throws IOException {
        verifyLongestConnection(Position.of(0, 2));
        verifyLongestConnection(Position.of(0, 3));
        verifyLongestConnection(Position.of(1, 0));
        verifyLongestConnection(Position.of(0, 2));
    }

    private void verifyLongestConnection(Position starting) {
        Set<Position> positions = new HashSet<>(handler.getConnectedPositions(grid, starting));
        Assert.assertEquals(expected, positions);
    }

    @Test
    public void testSingleConnection() {
        Set<Position> positions = handler.getConnectedPositions(grid, Position.of(3, 4));
        Assert.assertEquals(1, positions.size());
        Assert.assertEquals(Position.of(3, 4), positions.iterator().next());
    }

}
