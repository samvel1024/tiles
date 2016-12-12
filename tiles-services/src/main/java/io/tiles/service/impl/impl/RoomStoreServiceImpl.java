package io.tiles.service.impl.impl;

import io.tiles.core.Helper;
import io.tiles.room.Room;
import io.tiles.service.impl.RoomNotFoundException;
import io.tiles.service.impl.RoomStoreService;
import io.tiles.service.impl.ServerFullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

/**
 * Created by Samvel Abrahamyan 11/27/16.
 */
@Service
public class RoomStoreServiceImpl implements RoomStoreService {

    private final Map<Long, Room> roomStore;
    private final Random random;
    private final long roomIdFrom;
    private final long roomIdTo;
    private final int maxTries;

    @Autowired
    public RoomStoreServiceImpl(
            Random random,
            @Value("${roomStoreService.roomIdFrom}") long roomIdFrom,
            @Value("${roomStoreService.roomIdTo}") long roomIdTo,
            @Value("${roomStoreService.idSearchMaxTries}") int maxTries) {
        this.random = random;
        this.roomIdFrom = roomIdFrom;
        this.roomIdTo = roomIdTo;
        this.maxTries = maxTries;
        this.roomStore = new HashMap<>();
    }


    @Override
    public Long putRoom(Room room) {
        Optional<Long> tryTo = Helper.tryTo(
                this::randomLong,
                id -> !roomStore.containsKey(id),
                maxTries
        );
        Long id = tryTo.orElseThrow(ServerFullException::new);
        roomStore.put(id, room);
        return id;
    }

    @Override
    public Room getRoom(Long id) {
        Room room = roomStore.get(id);
        if (room == null)
            throw new RoomNotFoundException();
        return room;
    }

    private long randomLong() {
        long rand = random.nextLong();
        if (rand == Long.MIN_VALUE) {
            rand = Long.MAX_VALUE;
        }
        return roomIdFrom + Math.abs(rand) % (roomIdTo - roomIdFrom);
    }
}
