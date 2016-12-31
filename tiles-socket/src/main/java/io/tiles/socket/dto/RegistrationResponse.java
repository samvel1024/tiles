package io.tiles.socket.dto;

import io.tiles.core.PlayerAdded;
import io.tiles.core.grid.cell.Player;
import io.tiles.core.grid.cell.Position;
import io.tiles.socket.room.SocketPlayer;

import java.util.List;
import java.util.Set;

/**
 * Created by Samvel Abrahamyan 12/12/16.
 */
public class RegistrationResponse extends PlayerAdded {

    public final List<SocketPlayer> players;


    public RegistrationResponse(Set<Position> positions, Player player, List<SocketPlayer> players) {
        super(positions, player);
        this.players = players;
    }


}
