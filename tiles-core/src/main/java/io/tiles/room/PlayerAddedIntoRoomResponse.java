package io.tiles.room;

import io.tiles.core.PlayerAddedResponse;
import io.tiles.core.grid.cell.Player;
import io.tiles.core.grid.cell.Position;

import java.util.List;
import java.util.Set;

/**
 * Created by Samvel Abrahamyan 11/21/16.
 */
public class PlayerAddedIntoRoomResponse extends PlayerAddedResponse{

    private List<Player> players;

    public PlayerAddedIntoRoomResponse(List<Player> players, Set<Position> positions, Player player) {
        super(positions, player);
        this.players = players;
    }

    public List<Player> getPlayers(){
        return players;
    }

}
