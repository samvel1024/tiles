package io.tiles.room.core;

import io.tiles.room.core.grid.cell.Player;

/**
 * Created by Samvel Abrahamyan 11/16/16.
 */
public class OutOfFreeCellsException extends RuntimeException {

    private Player player;

    public OutOfFreeCellsException(Player player) {
        super("Cannot find cells for player " + player);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

}
