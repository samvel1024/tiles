package io.tiles.socket.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.tiles.core.grid.cell.Position;
import io.tiles.socket.room.SocketPlayer;
import io.tiles.socket.room.SocketPlayerRegistrar;

/**
 * Created by Samvel Abrahamyan 12/21/16.
 */
public class TurnHandler extends SimpleChannelInboundHandler<Position> {


    private final SocketPlayerRegistrar registrar;

    public TurnHandler(SocketPlayerRegistrar registrar){
        this.registrar = registrar;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Position position) throws Exception {
        SocketPlayer player = registrar.getPlayerForChannel(ctx.channel());
        player.room.makeTurn(position, player);
    }

}
