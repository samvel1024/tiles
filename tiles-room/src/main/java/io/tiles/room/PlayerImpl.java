package io.tiles.room;

import io.tiles.room.core.grid.cell.Player;

/**
 * Created by Samvel Abrahamyan 11/19/16.
 */
public class PlayerImpl implements Player {

    private String playerName;
    private String playerColor;
    private String playerId;

    public PlayerImpl(String playerName, String playerColor, String playerId) {
        this.playerName = playerName;
        this.playerColor = playerColor;
        this.playerId = playerId;
    }
}
