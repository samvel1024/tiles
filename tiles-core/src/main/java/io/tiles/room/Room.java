package io.tiles.room;

import io.tiles.core.PlayerAddedResponse;
import io.tiles.core.TurnResponse;
import io.tiles.core.World;
import io.tiles.core.grid.cell.Player;
import io.tiles.core.grid.cell.Position;

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

    public Room(World world) {
        this.world = world;
    }

    @Override
    public TurnResponse turn(Position position, Player byPlayer) {
        synchronized (mutex) {
            return world.turn(position, byPlayer);
        }
    }

    @Override
    public PlayerAddedResponse addPlayer(Player player) {
        synchronized (mutex) {
            PlayerAddedResponse playerAddedResponse = world.addPlayer(player);
            players.add(player);
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
