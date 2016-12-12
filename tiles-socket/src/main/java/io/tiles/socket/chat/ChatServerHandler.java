package io.tiles.socket.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.DefaultEventLoop;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

/**
 * Created by Samvel Abrahamyan 12/12/16.
 */
public class ChatServerHandler extends SimpleChannelInboundHandler<String> {


    private static ChannelGroup channels = new DefaultChannelGroup(new DefaultEventLoop());

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        for (Channel ch:channels){
            ch.writeAndFlush("[" +ctx.channel().remoteAddress() +  "] - " + msg);
        }
        System.out.println("[" +ctx.channel().remoteAddress() +  "] - " + msg);

    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
        Channel channel = ctx.channel();
        channels.add(channel);
        dispatchMessage("[SERVER] - "  + channel.remoteAddress() + " has joined");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);
        channels.remove(ctx.channel());
        dispatchMessage("[SERVER] - Removed " + ctx.channel().remoteAddress());
    }


    private void dispatchMessage(String message){
        for (Channel ch: channels){
            ch.writeAndFlush(message);
        }
        System.out.println(message);
    }
}
