package io.tiles.room.core.simpleworld.impl;

import io.tiles.room.core.grid.cell.Position;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Created by Samvel Abrahamyan 11/17/16.
 */
public class MockedRandom extends Random {

    private Queue<Position> queuedPositions = new LinkedList<>();
    private boolean rowWasRequested = false;

    public MockedRandom mock(Position position) {
        this.queuedPositions.add(position);
        return this;
    }

    @Override
    public int nextInt(int bound) {
        if (queuedPositions.isEmpty())
            return super.nextInt(bound);

        int ans = rowWasRequested ? queuedPositions.remove().col() : queuedPositions.element().row();
        rowWasRequested = !rowWasRequested;
        return ans;
    }
}
