package io.tiles.socket.room;

import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Samvel Abrahamyan 12/31/16.
 */
@Component
public class SocketPlayerRegistrar {

    private Map<Channel, SocketPlayer> socketPlayerMap = new ConcurrentHashMap<>();

    public SocketPlayer getPlayerForChannel(Channel channel) {
        SocketPlayer player = socketPlayerMap.get(channel);
        if (player == null) {
            throw new IllegalStateException("Can't find player for channel " + channel);
        }
        return player;
    }

    public void registerPlayer(Channel channel, SocketPlayer socketPlayer) {
        if (socketPlayerMap.containsKey(channel))
            throw new IllegalStateException("Channel " + channel + " is already registered");

        socketPlayerMap.put(channel, socketPlayer);
    }

    public void unregisterPlayer(Channel channel) {
        if (!socketPlayerMap.containsKey(channel))
            throw new IllegalStateException("Channel " + channel + " is not registered");
        socketPlayerMap.remove(channel);
    }

}
