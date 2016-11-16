package io.tiles.core;

import io.tiles.core.grid.cell.Player;

/**
 * Created by Samvel Abrahamyan 11/16/16.
 */
public class OutOfCellsException extends RuntimeException {

    private Player player;

    public OutOfCellsException(Player player){
        super("Cannot find cells for player " + player);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

}
