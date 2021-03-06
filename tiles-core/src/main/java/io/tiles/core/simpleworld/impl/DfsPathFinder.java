package io.tiles.core.simpleworld.impl;

import io.tiles.core.grid.Grid;
import io.tiles.core.grid.cell.CellShape;
import io.tiles.core.grid.cell.Direction;
import io.tiles.core.grid.cell.Cell;
import io.tiles.core.grid.cell.Position;
import io.tiles.core.simpleworld.ConnectionPathFinder;

import java.util.*;

/**
 * Created by Samvel Abrahamyan 11/14/16.
 */
public class DfsPathFinder implements ConnectionPathFinder {

    @Override
    public Set<Position> getConnectedPositions(Grid grid, Position startingPosition) {
        Set<Position> visited = new HashSet<>();
        Deque<QueueItem> queue = new ArrayDeque<>();
        Set<Position> connectedPositions = new LinkedHashSet<>();

        queue.add(getQueueItemForPosition(grid, startingPosition));
        visited.add(startingPosition);
        connectedPositions.add(startingPosition);

        while (!queue.isEmpty()) {
            QueueItem curr = queue.pop();
            curr.shape.getConnectionDirections()
                    .stream()
                    .map(direction -> new ProposedPosition(
                            curr.pos.movedBy(direction.unitMovement()),
                            direction
                    ))
                    .filter(proposed -> !visited.contains(proposed.position))
                    .filter(proposed -> proposed.position.isInside(grid.getSize()))
                    .filter(proposed -> grid
                            .getCellAt(proposed.position)
                            .getShape()
                            .getConnectionDirections()
                            .contains(proposed.directionFromPrev.opposite())
                    )
                    .forEach(proposed -> {
                        queue.add(getQueueItemForPosition(grid, proposed.position));
                        connectedPositions.add(proposed.position);
                        visited.add(proposed.position);
                    });
        }

        return connectedPositions;
    }


    private QueueItem getQueueItemForPosition(Grid grid, Position position) {
        Cell cell = grid.getCellAt(position);
        return new QueueItem(
                position,
                cell,
                cell.getShape()
        );
    }


    private static class ProposedPosition {
        final Position position;
        final Direction directionFromPrev;

        public ProposedPosition(Position position, Direction directionFromPrev) {
            this.position = position;
            this.directionFromPrev = directionFromPrev;
        }
    }

    private static class QueueItem {
        final Position pos;
        final Cell cell;
        final CellShape shape;

        public QueueItem(Position pos, Cell cell, CellShape shape) {
            this.pos = pos;
            this.cell = cell;
            this.shape = shape;
        }
    }

}
