package io.tiles.service.impl.impl;

import io.tiles.core.Turn;
import io.tiles.core.grid.cell.Position;
import io.tiles.service.impl.Compressor;

/**
 * Created by Samvel Abrahamyan 12/17/16.
 */
public class CompressorImpl implements Compressor {

    private final CompressorHelper helper;

    public CompressorImpl(Position size){
        helper = new CompressorHelper(size);
    }

    @Override
    public String encodeResponse(Turn response) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(helper.encodePosition(response.getRotatedPosition()));
        response.getChownedPostions().stream().map(helper::encodePosition).forEach(stringBuilder::append);
        return stringBuilder.toString();
    }

    @Override
    public Position decodeRequest(String str) {
        return helper.decodePosition(str.charAt(0));
    }



}

