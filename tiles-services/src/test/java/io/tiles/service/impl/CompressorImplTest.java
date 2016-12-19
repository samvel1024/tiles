package io.tiles.service.impl;

import io.tiles.core.TurnResponse;
import io.tiles.core.grid.cell.Position;
import io.tiles.service.impl.impl.CompressorHelper;
import io.tiles.service.impl.impl.CompressorImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Samvel Abrahamyan 12/17/16.
 */
public class CompressorImplTest {

    @Test
    public void test() {
        Random random = new Random();
        Position size = Position.of(100,200);
        Compressor server = new CompressorImpl(size);
        ClientCompressor client = new ClientCompressor(size);

        //request from client
        Position clientRequestDto = Position.of(random.nextInt(size.row), random.nextInt(size.col));
        String requestString = client.encodeRequest(clientRequestDto);
        Position serverRequestDto = server.decodeRequest(requestString);
        Assert.assertEquals(clientRequestDto, serverRequestDto);

        //response from server
        TurnResponse serverResponseDto = new TurnResponse(
                Position.of(
                        random.nextInt(size.row),
                        random.nextInt(size.col)
                ),
                IntStream
                        .range(0, 100)
                        .mapToObj(i -> Position.of(
                                random.nextInt(size.row),
                                random.nextInt(size.col)))
                        .collect(Collectors.toList())
        );
        String responseString = server.encodeResponse(serverResponseDto);
        TurnResponse clientResponseDto = client.decodeResponse(responseString);
        Assert.assertEquals(serverResponseDto, clientResponseDto);
    }



    private static class ClientCompressor {

        private final CompressorHelper helper;

        public ClientCompressor(Position size) {
            this.helper = new CompressorHelper(size);
        }

        public String encodeRequest(Position pos) {
            return "" + helper.encodePosition(pos);
        }

        public TurnResponse decodeResponse(String response) {
            Position turnedPos = helper.decodePosition(response.charAt(0));
            List<Position> positions = new ArrayList<>();
            for (int i = 1; i < response.length(); ++i) {
                positions.add(helper.decodePosition(response.charAt(i)));
            }

            return new TurnResponse(turnedPos, positions);

        }

    }


}
