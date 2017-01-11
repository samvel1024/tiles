package io.tiles.socket.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.tiles.service.impl.WorldStore;
import io.tiles.socket.dto.RegistrationRequestDto;
import io.tiles.socket.room.Room;
import io.tiles.socket.room.SocketPlayer;

/**
 * Created by Samvel Abrahamyan 12/21/16.
 */
public class RegistrationRequestHandler extends SimpleChannelInboundHandler<RegistrationRequestDto> {


    private final WorldStore<Room> roomWorldStore;

    public RegistrationRequestHandler(WorldStore<Room> roomWorldStore) {
        this.roomWorldStore = roomWorldStore;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RegistrationRequestDto msg) throws Exception {
        Room room = roomWorldStore.getWorld(msg.roomId);
        room.registerPlayer(new SocketPlayer.Builder()
                .setName(msg.name)
                .setColor(msg.color)
                .setRoom(room)
                .setChannel(ctx.channel())
        );
    }
}
