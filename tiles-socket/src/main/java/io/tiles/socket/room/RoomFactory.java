package io.tiles.socket.room;

import io.netty.channel.group.ChannelGroup;
import io.tiles.core.World;
import io.tiles.core.grid.GridFactory;
import io.tiles.core.grid.cell.Position;
import io.tiles.service.impl.Compressor;
import io.tiles.service.impl.WorldFactory;
import io.tiles.service.impl.impl.CompressorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Created by Samvel Abrahamyan 1/11/17.
 */
@Component
public class RoomFactory {

    private final SocketPlayerRegistrar registrar;
    private final WorldFactory baseWorldFactory;

    @Autowired
    public RoomFactory(WorldFactory worldFactory, GridFactory gridFactory, Random random, SocketPlayerRegistrar socketPlayerRegistrar) {
        this.registrar = socketPlayerRegistrar;
        this.baseWorldFactory = worldFactory;
    }

    public Room create(Position size, ChannelGroup channels) {
        World base = baseWorldFactory.create(size);
        Compressor compressor = new CompressorImpl(size);
        return new Room(
                channels,
                compressor,
                base,
                registrar
        );
    }
}
