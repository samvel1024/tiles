package io.tiles.socket.room;

import io.netty.channel.group.ChannelGroup;
import io.tiles.core.PlayerAdded;
import io.tiles.core.Turn;
import io.tiles.core.World;
import io.tiles.core.grid.cell.Player;
import io.tiles.core.grid.cell.Position;
import io.tiles.service.impl.Compressor;
import io.tiles.socket.dto.RegistrationResponse;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Samvel Abrahamyan 12/31/16.
 */
public class Room implements World {

    private final ChannelGroup channelGroup;
    private final Compressor compressor;
    private final Set<SocketPlayer> players = new LinkedHashSet<>();
    private final World baseWorld;
    private final SocketPlayerRegistrar registrar;

    public Room(
            ChannelGroup channelGroup,
            Compressor compressor,
            World baseWorld,
            SocketPlayerRegistrar registrar) {
        this.channelGroup = channelGroup;
        this.compressor = compressor;
        this.baseWorld = baseWorld;
        this.registrar = registrar;
    }

    @Override
    public synchronized Turn makeTurn(Position position, Player byPlayer) {
        Turn turn = baseWorld.makeTurn(position, byPlayer);
        channelGroup.writeAndFlush(compressor.encodeResponse(turn));
        return turn;
    }

    @Override
    public synchronized PlayerAdded addPlayer(Player player) {
        return baseWorld.addPlayer(player);
    }

    @Override
    public synchronized void removePlayer(Player player) {

    }

    public RegistrationResponse registerPlayer(SocketPlayer.Builder playerBuilder) {
        playerBuilder.setId(this.players.size());
        SocketPlayer player = playerBuilder.build();
        PlayerAdded added = this.addPlayer(player);
        this.players.add(player);
        RegistrationResponse response = new RegistrationResponse(
                added.getPositions(),
                added.getPlayer(),
                new ArrayList<>(this.players)
        );

        this.channelGroup.writeAndFlush(response);
        player.channel.writeAndFlush("");

        return response;
    }


    public void unregisterPlayer(SocketPlayer player){
        this.registrar.unregisterPlayer(player.channel);
    }


}
