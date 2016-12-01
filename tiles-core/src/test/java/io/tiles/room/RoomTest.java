package io.tiles.room;

import io.tiles.core.grid.RandomGridFactory;
import io.tiles.core.grid.cell.CellShape;
import io.tiles.core.grid.cell.Player;
import io.tiles.core.grid.cell.Position;
import io.tiles.core.simpleworld.impl.DfsPathFinder;
import io.tiles.core.simpleworld.impl.RandomizedRegistrar;
import io.tiles.core.simpleworld.impl.SimpleConnectionHandler;
import io.tiles.core.simpleworld.impl.SimpleWorld;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Samvel Abrahamyan 12/1/16.
 */
public class RoomTest {

    private Room room;

    @Before
    public void init(){
        room = new Room(new SimpleWorld(
                new RandomGridFactory(new Random()).create(Position.of(10, 20)),
                new DfsPathFinder(),
                new RandomizedRegistrar(new Random(), 1, new CellShape[][]{
                        {CellShape.RB, CellShape.BL},
                        {CellShape.UR, CellShape.LU}
                }),
                new SimpleConnectionHandler()
        ));
    }

    @Test
    public void testPlayerListContainsPlayers(){

        List<Player> players = new ArrayList<>();

        Player player1 = new Player() {};
        Player player2 = new Player() {};
        Player player3 = new Player() {};
        Player player4 = new Player() {};

        players.add(player1);
        Assert.assertEquals(players, room.addPlayer(player1).getPlayers());

        players.add(player2);
        Assert.assertEquals(players, room.addPlayer(player2).getPlayers());

        players.add(player3);
        Assert.assertEquals(players, room.addPlayer(player3).getPlayers());

        players.add(player4);
        Assert.assertEquals(players, room.addPlayer(player4).getPlayers());

    }


}
