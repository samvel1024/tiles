package io.tiles.service.impl.impl;

import io.tiles.core.TurnResponse;
import io.tiles.core.grid.cell.Position;
import io.tiles.service.impl.Compressor;

/**
 * Created by Samvel Abrahamyan 12/17/16.
 */
public class CompressorImpl implements Compressor {

    private final long nearestCol;


    public CompressorImpl(Position size){
        this.nearestCol = nextBinaryPow(size.col);
    }

    @Override
    public String encodeResponse(TurnResponse response) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(encodePosition(response.getRotatedPosition()));
        response.getChownedPostions().stream().map(this::encodePosition).forEach(stringBuilder::append);
        return stringBuilder.toString();
    }

    @Override
    public Position decodeRequest(String str) {
        long code = str.charAt(0);
        return Position.of((int) (code % nearestCol), (int) (code / nearestCol));
    }

    private char encodePosition(Position pos){
        long  res = pos.row;
        res *= nearestCol;
        res += pos.col;
        return (char) res;
    }

    private long nextBinaryPow(long n){
        long ans = 1;
        while(ans <= n)
            ans *= 2;
        return ans;
    }

}

