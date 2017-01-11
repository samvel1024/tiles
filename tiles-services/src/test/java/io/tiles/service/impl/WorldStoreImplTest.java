package io.tiles.service.impl;

import io.tiles.core.PlayerAdded;
import io.tiles.core.SynchronizedWorld;
import io.tiles.core.Turn;
import io.tiles.core.World;
import io.tiles.core.grid.Grid;
import io.tiles.core.grid.cell.Player;
import io.tiles.core.grid.cell.Position;
import io.tiles.service.impl.impl.WorldStoreImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * Created by Samvel Abrahamyan 12/1/16.
 */
public class WorldStoreImplTest {

    private Random random = new Random();
    private long idFrom = 100;
    private long idTo = 200;
    private WorldStoreImpl worldStore;

    @Before
    public void init() {
        worldStore = new WorldStoreImpl(random, idFrom, idTo, 10000);
    }


    @Test
    public void testWorldRetrieval() {

        SynchronizedWorld world = new SynchronizedWorld(new World() {

            @Override
            public Turn makeTurn(Position position, Player byPlayer) {
                return null;
            }

            @Override
            public PlayerAdded addPlayer(Player player) {
                return null;
            }

            @Override
            public void removePlayer(Player pl) {

            }

            @Override
            public Grid getState() {
                return null;
            }
        });

        long id = worldStore.putWorld(world);

        Assert.assertSame(world, worldStore.getWorld(id));
    }

    @Test(expected = ServerFullException.class)
    public void testServerFull() {
        Set<Long> existingIds = new HashSet<>();
        IntStream.range(0, (int) (idTo - idFrom)+1).forEach((i) -> {
            long id = worldStore.putWorld(new SynchronizedWorld(new World() {
                @Override
                public Turn makeTurn(Position position, Player byPlayer) {
                    return null;
                }

                @Override
                public PlayerAdded addPlayer(Player player) {
                    return null;
                }

                @Override
                public void removePlayer(Player pl) {

                }

                @Override
                public Grid getState() {
                    return null;
                }
            }));
            Assert.assertFalse(existingIds.contains(id));
            Assert.assertFalse( id < idFrom || id >= idTo);
            existingIds.add(id);
        });
    }

    @Test(expected = WorldNotFoundException.class)
    public void testWrongIdRetrieval(){
        worldStore.getWorld(0L);
    }

}
