package es.urjccode.mastercloudapps.adcs.draughts.models;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class GameTest {

    private Game game;

    public GameTest() {
        game = new Game();
    }

    @Test
    public void testGivenNewBoardThenGoodLocations() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < game.getDimension(); j++) {
                Coordinate coordinate = new Coordinate(i, j);
                Color color = game.getColor(coordinate);
                if (coordinate.isBlack()) {
                    assertEquals(Color.BLACK, color);
                } else {
                    assertNull(color);
                }
            }
        }
        for (int i = 5; i < game.getDimension(); i++) {
            for (int j = 0; j < game.getDimension(); j++) {
                Coordinate coordinate = new Coordinate(i, j);
                Color color = game.getColor(coordinate);
                if (coordinate.isBlack()) {
                    assertEquals(Color.WHITE, color);
                } else {
                    assertNull(color);
                }
            }
        }
    }

    @Test
    public void testGivenGameWhenMoveEmptySquaerThenEmptySquareError() {
        assertEquals(
            Error.EMPTY_ORIGIN,
            new Game().isCorrect(new Coordinate(4, 3), new Coordinate(3, 4))
        );
    }

    @Test
    public void testGivenGameWhenMoveOppositePieceThenError() {
        assertEquals(
            Error.OPPOSITE_PIECE,
            new Game().isCorrect(new Coordinate(2, 1), new Coordinate(3, 0))
        );
    }

    @Test
    public void testGivenGameWhenNotDiagonalMovementThenError() {
        assertEquals(
            Error.NOT_DIAGONAL,
            new Game().isCorrect(new Coordinate(5, 2), new Coordinate(4, 2))
        );
    }

    @Test
    public void testGivenGameWhenMoveWithNotAdvancedThenError() {
        Game game = new GameBuilder()
            .row(" n n n n")
            .row("n n n n ")
            .row(" n n n  ")
            .row("      n ")
            .row("       b")
            .row("b b b   ")
            .row(" b b b b")
            .row("b b b b ")
            .build();
        assertEquals(
            Error.NOT_ADVANCED,
            game.isCorrect(new Coordinate(4, 7), new Coordinate(5, 6))
        );
    }

    @Test
    public void testGivenGameWhenNotEmptyTargeThenError() {
        Game game = new GameBuilder()
            .row(" n n n n")
            .row("n n n n ")
            .row(" n n n  ")
            .row("      n ")
            .row("       b")
            .row("b b b   ")
            .row(" b b b b")
            .row("b b b b ")
            .build();
        assertEquals(
            Error.NOT_EMPTY_TARGET,
            game.isCorrect(new Coordinate(4, 7), new Coordinate(3, 6))
        );
    }

    @Test
    public void testGivenGameWhenCorrectMovementThenOk() {
        Coordinate origin = new Coordinate(5, 0);
        Coordinate target = new Coordinate(4, 1);
        this.game.move(origin, target);
        assertNull(this.game.getColor(origin));
        assertEquals(Color.WHITE, this.game.getColor(target));
        origin = new Coordinate(2, 3);
        target = new Coordinate(3, 4);
        this.game.move(origin, target);
        assertNull(this.game.getColor(origin));
        assertEquals(Color.BLACK, this.game.getColor(target));
    }

    @Test
    public void testGivenGameWhenMovementThenEatPiece() {
        Game game = new GameBuilder()
            .row(" n n n n")
            .row("n n n n ")
            .row("   n n n")
            .row("n       ")
            .row(" b b    ")
            .row("    b b ")
            .row(" b b b b")
            .row("b b b b ")
            .changeTurn()
            .build();
        Coordinate origin = new Coordinate(3, 0);
        Coordinate target = new Coordinate(5, 2);
        assertNull(game.isCorrect(origin, target));
        game.move(origin, target);
        assertNull(game.getColor(origin));
        assertNull(game.getColor(new Coordinate(4, 1)));
        assertEquals(Color.BLACK, game.getColor(target));
    }

    @Test
    public void testGivenGameWhenEatEmptyPieceThenError() {
        assertEquals(
            Error.EATING_EMPTY,
            new Game().isCorrect(new Coordinate(5, 4), new Coordinate(3, 2))
        );
    }

    @Test
    public void testGivenGameWhenMoveBadDistanceThenError() {
        Game game = new GameBuilder()
            .row(" n n n n")
            .row("n n n n ")
            .row(" n   n n")
            .row("  n     ")
            .row("       b")
            .row("b b b   ")
            .row(" b b b b")
            .row("b b b b ")
            .build();
        assertEquals(
            Error.BAD_DISTANCE,
            game.isCorrect(new Coordinate(5, 0), new Coordinate(2, 3))
        );
    }

}