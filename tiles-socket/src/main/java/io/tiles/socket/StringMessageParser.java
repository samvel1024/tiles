package io.tiles.socket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * Created by Samvel Abrahamyan 12/13/16.
 */
public class StringMessageParser extends MessageToMessageDecoder<String> {




    @Override
    protected void decode(ChannelHandlerContext ctx, String msg, List<Object> out) throws Exception {



    }


}
