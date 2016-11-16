package io.tiles.core;

import io.tiles.core.grid.Grid;
import io.tiles.core.grid.cell.Cell;
import io.tiles.core.grid.cell.CellShape;
import io.tiles.core.grid.cell.Player;
import io.tiles.core.grid.cell.Position;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created by Samvel Abrahamyan 11/16/16.
 */
public class RandomizedRegistarar implements GridPlayerRegistrar {

    private Random generator;
    private int padding;
    private CellShape[][] pattern;
    private Position patternSize;

    public RandomizedRegistarar(Random generator, int padding, CellShape[][] pattern) {
        this.generator = generator;
        this.padding = padding;
        this.pattern = pattern;
        this.patternSize = calculatePatternSize(pattern);
    }


    @Override
    public void registerPlayer(Grid grid, Player player) {
        Optional<Position> proposedPosition = tryTo(
                () -> generateRandomPosition(grid.getSize()),
                (position) -> isAllFreeInRegion(grid, position),
                calculateMaxAttemptCount(grid.getSize())
        );
        if (!proposedPosition.isPresent())
            throw new OutOfCellsException(player);

        Position leftUp = proposedPosition.get();

        for (int r = 0; r < pattern.length; r++) {
            for (int c = 0; c < pattern[r].length; c++) {
                Position curr = leftUp.movedBy(Position.of(r, c));
                Cell cell = grid.getCellAt(curr);
                cell.setOwner(player);
                cell.setShape(pattern[r][c]);
            }
        }

    }


    private boolean isAllFreeInRegion(Grid grid, Position leftUp) {
        for (int r = leftUp.row() - padding; r < leftUp.row() + patternSize.row() + padding; ++r) {
            for (int c = leftUp.col() - padding; c < leftUp.col() + patternSize.col() + padding; ++c) {
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
        return Position.of(generator.nextInt(bound.row()), generator.nextInt(bound.col()));
    }

    private int calculateMaxAttemptCount(Position size) {
        return (size.row() * size.col()) / Math.min(patternSize.row(), patternSize.col());
    }

    private static <T> Optional<T> tryTo(Supplier<T> supplier, Predicate<T> predicate, int attempts) {
        for (int count = 0; count < attempts; ++count) {
            T object = supplier.get();
            if (predicate.test(object))
                return Optional.of(object);
        }
        return Optional.empty();
    }

}
