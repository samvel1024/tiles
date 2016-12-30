package io.tiles.service.impl.impl;

import io.tiles.core.Helper;
import io.tiles.core.World;
import io.tiles.service.impl.WorldNotFoundException;
import io.tiles.service.impl.ServerFullException;
import io.tiles.service.impl.WorldStore;
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
public class WorldStoreImpl implements WorldStore<World> {

    private final Map<Long, World> worldStore;
    private final Random random;
    private final long worldIdFrom;
    private final long worldIdTo;
    private final int maxTries;

    @Autowired
    public WorldStoreImpl(
            Random random,
            @Value("${worldStoreService.worldIdFrom}") long worldIdFrom,
            @Value("${worldStoreService.worldIdTo}") long worldIdTo,
            @Value("${worldStoreService.idSearchMaxTries}") int maxTries) {
        this.random = random;
        this.worldIdFrom = worldIdFrom;
        this.worldIdTo = worldIdTo;
        this.maxTries = maxTries;
        this.worldStore = new HashMap<>();
    }


    @Override
    public Long putWorld(World world) {
        Optional<Long> tryTo = Helper.tryTo(
                this::randomLong,
                id -> !worldStore.containsKey(id),
                maxTries
        );
        Long id = tryTo.orElseThrow(ServerFullException::new);
        worldStore.put(id, world);
        return id;
    }

    @Override
    public World getWorld(Long id) {
        World world = worldStore.get(id);
        if (world == null)
            throw new WorldNotFoundException();
        return world;
    }

    private long randomLong() {
        long rand = random.nextLong();
        if (rand == Long.MIN_VALUE) {
            rand = Long.MAX_VALUE;
        }
        return worldIdFrom + Math.abs(rand) % (worldIdTo - worldIdFrom);
    }
}
