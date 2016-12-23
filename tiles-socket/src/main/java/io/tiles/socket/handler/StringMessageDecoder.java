package io.tiles.socket.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.tiles.socket.parser.EventParser;
import io.tiles.socket.parser.EventParserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Samvel Abrahamyan 12/13/16.
 */
public class StringMessageDecoder extends MessageToMessageDecoder<String> {

    private final EventParserMapper eventParserMapper;
    private final char EVENT_KEY_DELIMITER = ':';

    public StringMessageDecoder(@Autowired EventParserMapper eventParserMapper) {
        this.eventParserMapper = eventParserMapper;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, String msg, List<Object> out) throws Exception {
        EventParser<?> parser = eventParserMapper.getParserByKey(this.extractKey(msg));
        out.add(parser.parse(this.extractMessage(msg)));
    }

    private String extractMessage(String msg) {
        return msg.substring(msg.indexOf(EVENT_KEY_DELIMITER) + 1);
    }

    private String extractKey(String msg) {
        return msg.substring(0, msg.indexOf(EVENT_KEY_DELIMITER));
    }

}
