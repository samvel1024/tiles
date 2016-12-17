package io.tiles.service.impl.impl;

import io.tiles.core.TurnResponse;
import io.tiles.core.grid.cell.Position;
import io.tiles.service.impl.Compressor;

/**
 * Created by Samvel Abrahamyan 12/17/16.
 */
public class CompressorImpl implements Compressor {

    private final Position size;

    public CompressorImpl(Position size){
        this.size = size;
    }

    @Override
    public String encodeResponse(TurnResponse response) {
        return null;
    }

    @Override
    public Position decodeRequest(String str) {
        return null;
    }

    public long encodePosition(Position pos){
        long  res = pos.row;
//        res *= nearestC;
//        res += col;
        return res;
    }
}

