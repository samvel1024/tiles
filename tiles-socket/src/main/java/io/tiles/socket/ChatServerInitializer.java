package io.tiles.socket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.tiles.socket.handler.RegistrationRequestHandler;
import io.tiles.socket.handler.StringMessageDecoder;
import io.tiles.socket.parser.EventParserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Samvel Abrahamyan 12/12/16.
 */
@Component
public class ChatServerInitializer extends ChannelInitializer<SocketChannel> {


    private final EventParserMapper mapper;


    public ChatServerInitializer(@Autowired EventParserMapper eventParserMapper){
        this.mapper = eventParserMapper;
    }


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8129, Delimiters.lineDelimiter()));
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());
        pipeline.addLast("handler", new StringMessageDecoder(mapper));
        pipeline.addLast("registrationHandler", new RegistrationRequestHandler());

    }

}
