package io.tiles.socket.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.tiles.socket.dto.RegistrationRequestDto;

/**
 * Created by Samvel Abrahamyan 12/21/16.
 */
public class RegistrationRequestHandler extends SimpleChannelInboundHandler<RegistrationRequestDto> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RegistrationRequestDto msg) throws Exception {
        System.out.println(msg.toString());
    }
}
