package es.urjccode.mastercloudapps.adcs.draughts.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class GameWithDraughtsTest {

    @Mock
    Turn turn;

    @Mock
    Piece piece;

    @Mock
    Pawn pawn;
    
    @Mock
    Board board;

    @InjectMocks
    Game game;

    @Before
    public void before(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGivenGameWhenWhitePawnAtLimitThenNewDraugts(){
        Game game = new GameBuilder()
            .row("        ")
            .row("b       ")
            .row("        ")
            .row("  n     ")
            .row("        ")
            .row("        ")
            .row("        ")
            .row("        ")
            .build();
        Coordinate origin = new Coordinate(1,0);
        Coordinate target = new Coordinate(0,1);
        game.move(origin, target);
        assertNull(game.getPiece(origin));
        assertEquals(game.getPiece(target), new Draught(Color.WHITE));
    }

    @Test
    public void testGivenGameWhenPawnAtLimitAndEatingThenNewDraugts(){
        Coordinate origin = new Coordinate(2,1);
        Coordinate target = new Coordinate(0,3);
        when (turn.getColor()).thenReturn(Color.WHITE);
        when(board.isEmpty(origin)).thenReturn(false);
        when(board.getColor(origin)).thenReturn(Color.WHITE);
        when(board.getPiece(origin)).thenReturn(pawn);
        when(piece.isCorrect(origin, target, board)).thenReturn(null);
        when(board.remove(origin)).thenReturn(new Pawn(Color.WHITE));
        when(board.getPiece(target)).thenReturn(new Pawn(Color.WHITE));
        game.move(origin, target);
        verify(board).remove(origin.betweenDiagonal(target));
        verify(board).remove(target);
        verify(board).put(any(Coordinate.class), any(Draught.class));
    }

    @Test
    public void testGivenGameWhenBlackPawnAtLimitThenNewDraugts(){
        Game game = new GameBuilder()
            .row("        ")
            .row("b       ")
            .row("        ")
            .row("        ")
            .row("        ")
            .row("        ")
            .row("   n    ")
            .row("        ")
            .changeTurn()
            .build();
        Coordinate origin = new Coordinate(6,3);
        Coordinate target = new Coordinate(7,2);
        game.move(origin, target);
        assertNull(game.getPiece(origin));
        assertEquals(game.getPiece(target), new Draught(Color.BLACK));
    }

    @Test
    public void testGivenGameWhenWhitePawnNotAtLimitThenNoNewDraugts(){
        Game game = new GameBuilder()
            .row("        ")
            .row("        ")
            .row(" b      ")
            .row("  n     ")
            .row("        ")
            .row("        ")
            .row("        ")
            .row("        ")
            .build();
        Coordinate origin = new Coordinate(2,1);
        Coordinate target = new Coordinate(1,0);
        game.move(origin, target);
        assertNull(game.getPiece(origin));
        assertEquals(game.getPiece(target), new Pawn(Color.WHITE));
    }

    @Test
    public void testGivenGameWhenBlackPawnNotAtLimitThenNoNewDraugts(){
        Game game = new GameBuilder()
            .row("        ")
            .row("        ")
            .row(" b      ")
            .row("  n     ")
            .row("        ")
            .row("        ")
            .row("        ")
            .row("        ")
            .changeTurn()
            .build();
        Coordinate origin = new Coordinate(3,2);
        Coordinate target = new Coordinate(4,1);
        game.move(origin, target);
        assertNull(game.getPiece(origin));
        assertEquals(game.getPiece(target), new Pawn(Color.BLACK));
    }

    @Test
    public void testGivenGameWhenWhiteDraughNotAdvanceThenNoError(){
        Game game = new GameBuilder()
            .row(" B      ")
            .row("        ")
            .row("        ")
            .row("        ")
            .row("        ")
            .row("        ")
            .row("        ")
            .row("    N   ")
            .build();
        Coordinate origin = new Coordinate(0,1);
        Coordinate target = new Coordinate(1,0);
        System.out.println(game);
        assertNull(game.isCorrect(origin, target));
        game.move(origin, target);
        assertNull(game.getPiece(origin));
        assertEquals(game.getPiece(target), new Draught(Color.WHITE));
    }

    @Test
    public void testGivenGameWhenBlackDraughNotAdvanceThenNoError(){
        Game game = new GameBuilder()
            .row("        ")
            .row("        ")
            .row(" b      ")
            .row("        ")
            .row("        ")
            .row("        ")
            .row("        ")
            .row("    N   ")
            .changeTurn()
            .build();
        Coordinate origin = new Coordinate(7,4);
        Coordinate target = new Coordinate(6,3);
        System.out.println(game);
        assertNull(game.isCorrect(origin, target));
        game.move(origin, target);
        assertNull(game.getPiece(origin));
        assertEquals(game.getPiece(target), new Draught(Color.BLACK));
    }

    @Test
    public void testGivenGameWhenDraughMoveLongerThanTwoPiecesThenNoError(){
        Game game = new GameBuilder()
            .row(" B      ")
            .row("        ")
            .row("        ")
            .row("        ")
            .row("        ")
            .row("        ")
            .row("        ")
            .row("    N   ")
            .build();
        Coordinate origin = new Coordinate(0,1);
        Coordinate target = new Coordinate(3,4);
        System.out.println(game);
        assertNull(game.isCorrect(origin, target));
        game.move(origin, target);
        assertNull(game.getPiece(origin));
        assertEquals(game.getPiece(target), new Draught(Color.WHITE));
    }
}