package io.tiles.socket.dto;

import io.tiles.socket.room.SocketPlayer;

import java.util.List;

/**
 * Created by Samvel Abrahamyan 12/12/16.
 */
public class RoomStateDto {

    public final CellDto[][] grid;
    public final List<SocketPlayer> players;

    public RoomStateDto(CellDto[][] grid, List<SocketPlayer> players) {
        this.grid = grid;
        this.players = players;
    }

}
