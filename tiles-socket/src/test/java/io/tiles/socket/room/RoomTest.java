package io.tiles.socket.room;

import io.netty.channel.group.ChannelGroup;
import io.tiles.core.World;
import io.tiles.service.impl.Compressor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Samvel Abrahamyan 1/1/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class RoomTest {

    @Mock
    private ChannelGroup channels;
    @Mock
    private Compressor compressor;
    @Mock
    private World baseWorld;
    @Mock
    private SocketPlayerRegistrar registrar;


    @Test
    public void registersPlayers(){
        List<SocketPlayer> playerList = new ArrayList<>();
//        Room room = new Room(channels, compressor, playerList, baseWorld, registrar);
//        SocketPlayer p1 = new SocketPlayer("P1", "#eeddff", 0, Mockito.mock(Channel.class), room);
//        Channel p1Channel = Mockito.mock(Channel.class);
//        SocketPlayer.Builder p2Builder = new SocketPlayer.Builder()
//                .setChannel(p1Channel)
//                .setRoom(room)
//                .setName("P2")
//                .setColor("#3912ee");
//        playerList.add(p1);
//
//        when(baseWorld.getState()).thenReturn(new G1rid(null, null));
//
//
//        room.registerPlayer(p2Builder);
//
//        verify(p1Channel).writeAndFlush(eq(new RoomStateDto()));
//

    }


}
