package io.tiles.service.impl;

import io.tiles.core.TurnResponse;
import io.tiles.core.grid.cell.Player;
import io.tiles.core.grid.cell.Position;
import io.tiles.room.PlayerAddedIntoRoomResponse;
import io.tiles.service.RoomService;
import io.tiles.service.RoomStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Samvel Abrahamyan 11/24/16.
 */
@Service
public class RoomServiceImpl implements RoomService {

    private final RoomStoreService roomStoreService;

    @Autowired
    public RoomServiceImpl(RoomStoreService roomStoreService) {
        this.roomStoreService = roomStoreService;
    }

    @Override
    public void createRoom(Long Long) {

    }

    @Override
    public PlayerAddedIntoRoomResponse addPlayer(Long key, Player player) {
        return null;
    }

    @Override
    public TurnResponse turn(Long key, Position position, Player player) {
        return null;
    }
}
