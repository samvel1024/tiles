package io.tiles.service.impl.impl;

import io.tiles.core.grid.GridFactory;
import io.tiles.core.grid.cell.CellShape;
import io.tiles.core.grid.cell.Position;
import io.tiles.core.simpleworld.impl.DfsPathFinder;
import io.tiles.core.simpleworld.impl.RandomizedRegistrar;
import io.tiles.core.simpleworld.impl.SimpleConnectionHandler;
import io.tiles.core.simpleworld.impl.SimpleWorld;
import io.tiles.room.Room;
import io.tiles.service.impl.WorldFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

import static io.tiles.core.grid.cell.CellShape.*;

/**
 * Created by Samvel Abrahamyan 11/27/16.
 */
@Service
public class SimpleRoomFactory implements WorldFactory {

    private final GridFactory gridFactory;
    private final Random random;

    @Autowired
    public SimpleRoomFactory(GridFactory gridFactory, Random random) {
        this.gridFactory = gridFactory;
        this.random = random;
    }

    @Override
    public Room create(Position size) {
        return new Room(
                new SimpleWorld(
                        gridFactory.create(size),
                        new DfsPathFinder(),
                        new RandomizedRegistrar(
                                random,
                                1,
                                new CellShape[][]{
                                        {RB, BL},
                                        {UR, LU}
                                }
                        ),
                        new SimpleConnectionHandler()
                ));
    }

}
