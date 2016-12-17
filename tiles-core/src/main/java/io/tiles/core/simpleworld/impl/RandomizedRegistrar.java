package io.tiles.core.simpleworld.impl;

import io.tiles.core.OutOfFreeCellsException;
import io.tiles.core.grid.Grid;
import io.tiles.core.grid.cell.Cell;
import io.tiles.core.grid.cell.CellShape;
import io.tiles.core.grid.cell.Player;
import io.tiles.core.grid.cell.Position;
import io.tiles.core.simpleworld.GridPlayerRegistrar;

import java.util.*;

import static io.tiles.core.Helper.tryTo;

/**
 * Created by Samvel Abrahamyan 11/16/16.
 */
public class RandomizedRegistrar implements GridPlayerRegistrar {

    private Random generator;
    private int padding;
    private CellShape[][] pattern;
    private Position patternSize;

    public RandomizedRegistrar(Random generator, int padding, CellShape[][] pattern) {
        this.generator = generator;
        this.padding = padding;
        this.pattern = pattern;
        this.patternSize = calculatePatternSize(pattern);
    }


    @Override
    public Set<Position> registerPlayer(Grid grid, Player player) {
        Optional<Position> proposedPosition = tryTo(
                () -> generateRandomPosition(grid.getSize()),
                (position) -> isAllFreeInRegion(grid, position),
                calculateMaxAttemptCount(grid.getSize())
        );
        if (!proposedPosition.isPresent())
            throw new OutOfFreeCellsException(player);

        Position leftUp = proposedPosition.get();
        Set<Position> newPositions = new LinkedHashSet<>();
        for (int r = 0; r < pattern.length; r++) {
            for (int c = 0; c < pattern[r].length; c++) {
                Position currPosition = leftUp.movedBy(Position.of(r, c));
                if (!currPosition.isInside(grid.getSize()))
                    continue;
                Cell cell = grid.getCellAt(currPosition);
                cell.setOwner(player);
                cell.setShape(pattern[r][c]);
                newPositions.add(currPosition);
            }
        }
        return newPositions;
    }

    private boolean isAllFreeInRegion(Grid grid, Position leftUp) {
        for (int r = leftUp.row - padding; r < leftUp.row + patternSize.row + padding; ++r) {
            for (int c = leftUp.col - padding; c < leftUp.col + patternSize.col + padding; ++c) {
                Position currPos = Position.of(r, c);
                if (!currPos.isInside(grid.getSize()))
                    continue;
                if (grid.getCellAt(currPos).isOwned())
                    return false;
            }
        }
        return true;
    }

    @Override
    public void unregisterPlayer(Grid grid, Player player) {
        throw new UnsupportedOperationException("Not implemented");
    }

    private Position calculatePatternSize(CellShape[][] pattern) {
        return Position.of(
                pattern.length,
                Arrays.stream(pattern)
                        .max((row1, row2) -> Integer.compare(row1.length, row2.length))
                        .get()
                        .length
        );
    }

    private Position generateRandomPosition(Position bound) {
        return Position.of(generator.nextInt(bound.row - patternSize.row), generator.nextInt(bound.col - patternSize.col));
    }

    private int calculateMaxAttemptCount(Position size) {
        return (size.row * size.col) / Math.min(patternSize.row, patternSize.col);
    }

}
