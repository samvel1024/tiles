package io.tiles.service.impl;

import io.tiles.core.Turn;
import io.tiles.core.grid.cell.Position;

/**
 * Created by Samvel Abrahamyan 12/17/16.
 */
public interface Compressor {

    String encodeResponse(Turn response);
    Position decodeRequest(String str);

}
