package io.tiles.core.simpleworld.impl;

import io.tiles.core.grid.Grid;
import io.tiles.core.grid.cell.CellShape;
import io.tiles.core.grid.cell.Player;
import io.tiles.core.OutOfFreeCellsException;
import io.tiles.core.PlayerAddedResponse;
import io.tiles.core.UnauthorizedTurnException;
import io.tiles.core.World;
import io.tiles.core.grid.cell.Position;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static io.tiles.core.simpleworld.impl.SimpleWorldTest.TurnItem.turn;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.fail;

/**
 * Created by Samvel Abrahamyan 11/17/16.
 */
public class SimpleWorldTest {

    private List<TurnItem> responsePerTurn = new ArrayList<>();

    @Before
    public void init() {
        responsePerTurn.add(turn(player1, Position.of(1, 1), Collections.emptyList()));
        responsePerTurn.add(turn(player1, Position.of(1, 1), Arrays.asList(
                Position.of(1, 2),
                Position.of(2, 2),
                Position.of(3, 2),
                Position.of(3, 3),
                Position.of(2, 3),
                Position.of(2, 4)
        )));
        responsePerTurn.add(turn(player2, Position.of(0, 3), Arrays.asList(
                Position.of(0, 2),
                Position.of(0, 1),
                Position.of(0, 0)
        )));
        responsePerTurn.add(turn(player2, Position.of(1, 4), Collections.emptyList()));
        responsePerTurn.add(turn(player1, Position.of(1, 1), Collections.emptyList()));
        responsePerTurn.add(turn(player2, Position.of(1, 4), Arrays.asList(
                Position.of (2, 4),
                Position.of (2, 3),
                Position.of (3, 3),
                Position.of (3, 2),
                Position.of (2, 2),
                Position.of (1, 2),
                Position.of (1, 1),
                Position.of (2, 1),
                Position.of (2, 0),
                Position.of (1, 0)
        )));
    }

    private int[][] initialState = new int[][]{
            {1, 4, 4, 1, 2},
            {5, 1, 2, 0, 5},
            {0, 3, 5, 1, 3},
            {4, 4, 0, 3, 5}

    };
    private int[][] stateAfterPlayersAdded = new int[][]{
            {1, 4, 4, 1, 2},
            {1, 2, 2, 0, 3},
            {0, 3, 5, 1, 3},
            {4, 4, 0, 3, 5}
    };

    private Player player1 = new Player() {
    };
    private Player player2 = new Player() {
    };
    private MockedRandom random = new MockedRandom();
    private Grid grid = new MatrixParsedGridFactory(initialState).create();
    private World world = new SimpleWorld(
            grid,
            new DfsPathFinder(),
            new RandomizedRegistrar(random, 1, new CellShape[][]{
                    {CellShape.RB, CellShape.BL},
                    {CellShape.UR, CellShape.LU}
            }),
            new SimpleConnectionHandler()
    );


    @Test
    public void testGamePlay() {

        random.mock(Position.of(1, 0)).mock(Position.of(0, 3));

        PlayerAddedResponse player1Added = world.addPlayer(player1);
        PlayerAddedResponse player2Added = world.addPlayer(player2);

        Assert.assertEquals(new HashSet<>(Arrays.asList(
                Position.of(1, 0),
                Position.of(1, 1),
                Position.of(2, 0),
                Position.of(2, 1)
        )), player1Added.getPositions());
        Assert.assertEquals(new HashSet<>(Arrays.asList(
                Position.of(0, 3),
                Position.of(0, 4),
                Position.of(1, 3),
                Position.of(1, 4)
        )), player2Added.getPositions());
        player1Added.getPositions().forEach(pos -> Assert.assertTrue(grid.getCellAt(pos).isOwnedBy(player1)));
        player2Added.getPositions().forEach(pos -> Assert.assertTrue(grid.getCellAt(pos).isOwnedBy(player2)));

        Assert.assertArrayEquals(stateAfterPlayersAdded, extractIntMatrixConfig(grid));

        //Check that no more cells are left
        try {
            world.addPlayer(new Player() {
            });
            fail("Should throw OutOfFreeCellsException");
        } catch (OutOfFreeCellsException e) {
            //expected
        }


        //Run turns
        responsePerTurn.forEach((turn) -> {
            Set<Position> response = world.turn(turn.position, turn.player).getChownedPostions();
            Assert.assertEquals(turn.response, response);
            response.forEach(pos -> assertTrue(grid.getCellAt(pos).isOwnedBy(turn.player)));
        });

        try {
            world.turn(Position.of(1, 0), player1);
            fail("Should throw UnauthorizedTurnException");
        } catch (UnauthorizedTurnException e) {
            //expected
        }

    }


    private int[][] extractIntMatrixConfig(Grid grid) {
        int[][] config = new int[grid.getSize().row()][grid.getSize().col()];

        for (int r = 0; r < config.length; r++) {
            for (int c = 0; c < config[r].length; c++) {
                config[r][c] = grid.getCellAt(Position.of(r, c)).getShape().ordinal();
            }
        }

        return config;
    }


    public static class TurnItem {
        Player player;
        Position position;
        Set<Position> response;

        public TurnItem(Player player, Position position, Set<Position> response) {
            this.player = player;
            this.position = position;
            this.response = response;
        }

        public static TurnItem turn(Player player, Position position, List<Position> response){
            return new TurnItem(player, position, new HashSet<>(response));
        }

    }

}
