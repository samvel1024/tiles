package io.tiles.service.impl;

import io.tiles.core.PlayerAddedResponse;
import io.tiles.core.TurnResponse;
import io.tiles.core.World;
import io.tiles.core.grid.cell.Player;
import io.tiles.core.grid.cell.Position;
import io.tiles.room.Room;
import io.tiles.service.impl.impl.RoomStoreServiceImpl;
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
public class RoomStoreServiceImplTest {

    private Random random = new Random();
    private long idFrom = 100;
    private long idTo = 200;
    private RoomStoreService roomStoreService;

    @Before
    public void init() {
        roomStoreService = new RoomStoreServiceImpl(random, idFrom, idTo, 10000);
    }


    @Test
    public void testRoomRetrieval() {

        Room room = new Room(new World() {
            @Override
            public TurnResponse turn(Position position, Player byPlayer) {
                return null;
            }

            @Override
            public PlayerAddedResponse addPlayer(Player player) {
                return null;
            }

            @Override
            public void removePlayer() {

            }
        });

        long id = roomStoreService.putRoom(room);

        Assert.assertSame(room, roomStoreService.getRoom(id));
    }

    @Test(expected = ServerFullException.class)
    public void testServerFull() {
        Set<Long> existingIds = new HashSet<>();
        IntStream.range(0, (int) (idTo - idFrom)+1).forEach((i) -> {
            long id = roomStoreService.putRoom(new Room(new World() {
                @Override
                public TurnResponse turn(Position position, Player byPlayer) {
                    return null;
                }

                @Override
                public PlayerAddedResponse addPlayer(Player player) {
                    return null;
                }

                @Override
                public void removePlayer() {

                }
            }));
            Assert.assertFalse(existingIds.contains(id));
            Assert.assertFalse( id < idFrom || id >= idTo);
            existingIds.add(id);
        });
    }

    @Test(expected = RoomNotFoundException.class)
    public void testWrongIdRetrieval(){
        roomStoreService.getRoom(0L);
    }

}
