package io.tiles.socket.room;

import io.netty.channel.group.ChannelGroup;
import io.tiles.core.PlayerAdded;
import io.tiles.core.Turn;
import io.tiles.core.World;
import io.tiles.core.grid.Grid;
import io.tiles.core.grid.cell.Player;
import io.tiles.core.grid.cell.Position;
import io.tiles.service.impl.Compressor;
import io.tiles.socket.dto.CellDto;
import io.tiles.socket.dto.RoomStateDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Samvel Abrahamyan 12/31/16.
 */
public class Room implements World {

    private final ChannelGroup channelGroup;
    private final Compressor compressor;
    private final List<SocketPlayer> players;
    private final World baseWorld;
    private final SocketPlayerRegistrar registrar;

    public Room(
            ChannelGroup channelGroup,
            Compressor compressor,
            List<SocketPlayer> players,
            World baseWorld,
            SocketPlayerRegistrar registrar) {
        this.channelGroup = channelGroup;
        this.compressor = compressor;
        this.players = players;
        this.baseWorld = baseWorld;
        this.registrar = registrar;
    }


    public Room(
            ChannelGroup channelGroup,
            Compressor compressor,
            World baseWorld,
            SocketPlayerRegistrar registrar) {
        this(channelGroup, compressor, new ArrayList<>(), baseWorld, registrar);
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

    @Override
    public synchronized Grid getState() {
        return baseWorld.getState();
    }

    public PlayerAdded registerPlayer(SocketPlayer.Builder playerBuilder) {
        int playerId = this.players.size();
        playerBuilder.setId(playerId);
        SocketPlayer player = playerBuilder.build();
        //Notify the player about the current state
        player.channel.writeAndFlush(buildRoomState());
        this.players.add(player);
        PlayerAdded added = this.addPlayer(player);
        //Notify all players about added player
        this.channelGroup.writeAndFlush(added);
        //Add into the registrar
        this.registrar.registerPlayer(player.channel, player);
        return added;
    }

    public void unregisterPlayer(SocketPlayer player) {
        this.registrar.unregisterPlayer(player.channel);
    }

    private RoomStateDto buildRoomState() {
        Grid grid = getState();
        CellDto[][] cells = new CellDto[grid.getSize().row][grid.getSize().col];
        for (int r = 0; r < grid.getSize().row; ++r) {
            for (int c = 0; c < grid.getSize().col; ++c) {
                Position currPos = Position.of(r, c);
                int currCellOwnerId = grid.getCellAt(currPos).isOwned() ?
                        ((SocketPlayer) grid.getCellAt(currPos).getOwner()).id : -1;
                cells[r][c] = new CellDto(currCellOwnerId, grid.getCellAt(currPos).getShape().ordinal());
            }
        }

        return new RoomStateDto(cells, players);
    }





}
