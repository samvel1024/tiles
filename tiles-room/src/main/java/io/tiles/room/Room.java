package io.tiles.room;

import io.tiles.room.core.PlayerAddedResponse;
import io.tiles.room.core.TurnResponse;
import io.tiles.room.core.World;
import io.tiles.room.core.grid.cell.Player;
import io.tiles.room.core.grid.cell.Position;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Samvel Abrahamyan 11/19/16.
 */
public class Room implements World {

    private World world;
    private String name;
    private Set<Player> players = new LinkedHashSet<>();
    private final Object mutex = new Object();

    public Room(World world, String name) {
        this.world = world;
        this.name = name;
    }

    @Override
    public TurnResponse turn(Position position, Player byPlayer) {
        synchronized (mutex) {
            return world.turn(position, byPlayer);
        }
    }

    @Override
    public PlayerAddedIntoRoomResponse addPlayer(Player player) {
        synchronized (mutex) {
            players.add(player);
            PlayerAddedResponse playerAddedResponse = world.addPlayer(player);
            return new PlayerAddedIntoRoomResponse(
                    new ArrayList<>(players),
                    playerAddedResponse.getPositions(),
                    playerAddedResponse.getPlayer()
            );
        }
    }

    @Override
    public void removePlayer() {
        throw new UnsupportedOperationException("Not supported");
    }

}
