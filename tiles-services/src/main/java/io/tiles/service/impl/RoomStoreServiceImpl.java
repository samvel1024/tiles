package io.tiles.service.impl;

import io.tiles.core.Helper;
import io.tiles.core.grid.cell.Position;
import io.tiles.room.Room;
import io.tiles.service.RoomStoreService;
import io.tiles.service.ServerFullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Samvel Abrahamyan 11/27/16.
 */
@Service
public class RoomStoreServiceImpl implements RoomStoreService {

    private final Map<Long, Room> roomStore = new ConcurrentHashMap<>();

    private final RoomFactory factory;
    private final Random random;
    @Value("${roomStoreService.roomIdFrom}")
    private long roomIdFrom;
    @Value("${roomStoreService.roomIdTo}")
    private long roomIdTo;

    @Autowired
    public RoomStoreServiceImpl(RoomFactory factory, Random random) {
        this.factory = factory;
        this.random = random;
    }


    @Override
    public Long putRoom(Position size) {
        Optional<Long> tryTo = Helper.tryTo(
                () -> roomIdTo + random.nextLong() % (roomIdTo - roomIdFrom),
                id -> !roomStore.containsKey(id),
                1000
        );
        Long id = tryTo.orElseThrow(ServerFullException::new);
        roomStore.put(id, factory.create(size));
        return id;
    }

    @Override
    public Room getRoom(Long key) {
        return roomStore.get(key);
    }
}
