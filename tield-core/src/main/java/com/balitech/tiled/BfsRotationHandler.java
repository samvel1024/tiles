package com.balitech.tiled;

import com.balitech.tiled.grid.Grid;
import com.balitech.tiled.grid.cell.*;

import java.util.*;

/**
 * Created by Samvel Abrahamyanon 11/14/16.
 */
public class BfsRotationHandler implements RotationHandler {


    public List<Position> getConnectedPositions(Grid grid, Position rotatePosition) {
        Set<Position> visited = new HashSet<>();
        Queue<QueueItem> queue = new LinkedList<>();
        List<Position> connectedPositions = new ArrayList<>();

        queue.add(getQueueItemForPosition(grid, rotatePosition));
        visited.add(rotatePosition);
        connectedPositions.add(rotatePosition);

        while (!queue.isEmpty()) {
            QueueItem curr = queue.remove();
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
                cell.getShape(),
                cell.getOwner()
        );
    }


    private static class ProposedPosition {
        Position position;
        Direction directionFromPrev;

        public ProposedPosition(Position position, Direction directionFromPrev) {
            this.position = position;
            this.directionFromPrev = directionFromPrev;
        }
    }

    private static class QueueItem {
        Position pos;
        Cell cell;
        CellShape shape;
        Player owner;

        public QueueItem(Position pos, Cell cell, CellShape shape, Player owner) {
            this.pos = pos;
            this.cell = cell;
            this.shape = shape;
            this.owner = owner;
        }
    }

}
