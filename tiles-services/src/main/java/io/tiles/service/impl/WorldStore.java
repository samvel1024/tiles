package io.tiles.service.impl;

import io.tiles.core.World;

/**
 * Created by Samvel Abrahamyan 11/27/16.
 */
public interface WorldStore<T extends World> {

    Long putWorld(T world);

    T getWorld(Long id);

}
