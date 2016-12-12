package io.tiles.socket.dto;

import io.tiles.core.grid.cell.CellShape;

/**
 * Created by Samvel Abrahamyan 12/12/16.
 */
public class CellDto {

    public final int playerId;
    public final CellShape cellShape;


    public CellDto(int playerId, CellShape cellShape) {
        this.playerId = playerId;
        this.cellShape = cellShape;
    }
}
