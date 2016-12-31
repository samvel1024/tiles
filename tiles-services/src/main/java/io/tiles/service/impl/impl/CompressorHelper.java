package io.tiles.service.impl.impl;

import io.tiles.core.grid.cell.Position;

/**
 * Created by Samvel Abrahamyan 12/18/16.
 */
public class CompressorHelper {

    private final int nearest;

    public CompressorHelper(Position size) {
        this.nearest = nextBinaryPow(size.col);
    }

    public char encodePosition(Position pos) {
        int res = pos.row;
        res *= nearest;
        res += pos.col;
        return (char) res;
    }

    public Position decodePosition(char c) {
        int code = (int) c;
        return Position.of((code / nearest), (code % nearest));
    }

    private int nextBinaryPow(int n) {
        int ans = 1;
        while (ans <= n)
            ans *= 2;
        return ans;
    }

}
