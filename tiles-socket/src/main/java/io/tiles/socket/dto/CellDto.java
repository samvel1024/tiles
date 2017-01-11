package io.tiles.socket.dto;

/**
 * Created by Samvel Abrahamyan 12/12/16.
 */
public class CellDto {

    public final int playerId;
    public final int cellShape;


    public CellDto(int playerId, int cellShape) {
        this.playerId = playerId;
        this.cellShape = cellShape;
    }
}
