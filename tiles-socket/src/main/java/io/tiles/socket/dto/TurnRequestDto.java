package io.tiles.socket.dto;

import io.tiles.core.grid.cell.Position;

/**
 * Created by Samvel Abrahamyan 12/21/16.
 */
public class TurnRequestDto {

    public final Position position;

    public TurnRequestDto(Position position) {
        this.position = position;
    }
}
