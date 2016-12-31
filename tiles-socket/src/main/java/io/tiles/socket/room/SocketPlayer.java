package io.tiles.socket.room;

import io.netty.channel.Channel;
import io.tiles.core.grid.cell.Player;

/**
 * Created by Samvel Abrahamyan 12/31/16.
 */
public class SocketPlayer implements Player {

    public final String name;
    public final String color;
    public final int id;
    public final transient Channel channel;
    public final transient Room room;

    public SocketPlayer(String name, String color, int id, Channel channel, Room room) {
        this.name = name;
        this.color = color;
        this.id = id;
        this.channel = channel;
        this.room = room;
    }

    public static class Builder {
        private String name;
        private String color;
        private Channel channel;
        private Room room;
        private int id;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setColor(String color) {
            this.color = color;
            return this;
        }

        public Builder setChannel(Channel channel) {
            this.channel = channel;
            return this;
        }

        public Builder setRoom(Room room) {
            this.room = room;
            return this;
        }
        
        public Builder setId(int id){
            this.id = id;
            return this;
        }
        
        public SocketPlayer build(){
            return new SocketPlayer(
                    name,
                    color,
                    id,
                    channel,
                    room
            );
        }
    }
    
    
}
