package io.tiles.service.impl.impl;

import io.tiles.core.TurnResponse;
import io.tiles.core.grid.cell.Player;
import io.tiles.core.grid.cell.Position;
import io.tiles.room.PlayerAddedIntoRoomResponse;
import io.tiles.room.Room;
import io.tiles.service.impl.RoomService;
import io.tiles.service.impl.RoomStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Samvel Abrahamyan 11/24/16.
 */
@Service
public class RoomServiceImpl implements RoomService {

    private final RoomStoreService roomStoreService;
    private final SimpleRoomFactory factory;


    @Autowired
    public RoomServiceImpl(RoomStoreService roomStoreService, SimpleRoomFactory factory) {
        this.roomStoreService = roomStoreService;
        this.factory = factory;
    }

    @Override
    public Long createRoom(Position size) {
        Room room = factory.create(size);
        return roomStoreService.putRoom(room);
    }

    @Override
    public PlayerAddedIntoRoomResponse addPlayer(Long id, Player player) {
        Room room = roomStoreService.getRoom(id);
        return room.addPlayer(player);
    }

    @Override
    public TurnResponse turn(Long id, Position position, Player player) {
        Room room = roomStoreService.getRoom(id);
        return room.turn(position, player);
    }
}
