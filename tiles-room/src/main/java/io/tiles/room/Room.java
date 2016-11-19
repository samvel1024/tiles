package io.tiles.room;

import io.tiles.room.core.TurnResponse;
import io.tiles.room.core.World;
import io.tiles.room.core.grid.cell.Player;
import io.tiles.room.core.grid.cell.Position;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Samvel Abrahamyan 11/19/16.
 */
public class Room{

    private World world;
    private String name;
    private Set<Player> players = new HashSet<>();
    private final Object lock = new Object();

    public Room(World world, String name){
        this.world = world;
        this.name = name;
    }

    public TurnResponse turn(Position position, Player byPlayer) {
        synchronized (lock){
            return world.turn(position, byPlayer);
        }
    }

    public PlayerAddedIntoRoomResponse addPlayer(PlayerAddRequest request) {
        synchronized (lock){
//            PlayerAddedResponse response =  world.addPlayer();
            return null;//new PlayerAddedIntoRoomResponse(response.getPositions(), response.getPlayer());
        }
    }

    public void removePlayer() {
        throw new UnsupportedOperationException("Not supported");
    }

}
