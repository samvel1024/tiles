package io.tiles.socket.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.tiles.socket.dto.TurnRequestDto;

/**
 * Created by Samvel Abrahamyan 12/21/16.
 */
public class TurnHandler extends SimpleChannelInboundHandler<TurnRequestDto> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TurnRequestDto msg) throws Exception {
        System.out.println(msg);
    }

}
