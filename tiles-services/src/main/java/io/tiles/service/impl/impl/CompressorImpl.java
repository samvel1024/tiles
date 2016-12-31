package io.tiles.service.impl.impl;

import io.tiles.core.Turn;
import io.tiles.core.grid.cell.Position;
import io.tiles.service.impl.Compressor;

import java.util.stream.Collectors;

/**
 * Created by Samvel Abrahamyan 12/17/16.
 */
public class CompressorImpl implements Compressor {

    private final CompressorHelper helper;

    public CompressorImpl(Position size) {
        helper = new CompressorHelper(size);
    }

    @Override
    public String encodeResponse(Turn response) {
        return String.valueOf(helper.encodePosition(response.getRotatedPosition())) +
                response.getChownedPostions().stream()
                        .map(helper::encodePosition)
                        .map(String::valueOf)
                        .collect(Collectors.joining());
    }

    @Override
    public Position decodeRequest(String str) {
        return helper.decodePosition(str.charAt(0));
    }


}

