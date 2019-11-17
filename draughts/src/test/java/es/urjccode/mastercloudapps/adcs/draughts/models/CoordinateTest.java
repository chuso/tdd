package es.urjccode.mastercloudapps.adcs.draughts.models;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CoordinateTest {

    @Test
    public void testGivenTwoCoordinatesWhenBettweenDiagonalThenOk() {
        assertEquals(new Coordinate(1, 1), new Coordinate(2, 2).betweenDiagonal(new Coordinate(0, 0)));
        assertEquals(new Coordinate(3, 1), new Coordinate(2, 2).betweenDiagonal(new Coordinate(4, 0)));
        assertEquals(new Coordinate(3, 3), new Coordinate(2, 2).betweenDiagonal(new Coordinate(4, 4)));
        assertEquals(new Coordinate(1, 3), new Coordinate(2, 2).betweenDiagonal(new Coordinate(0, 4)));
    }

    @Test
    public void testGivenTwoCoordinatesWhenGetDistanceThenResult() {
        assertEquals(3, new Coordinate(3, 4).diagonalDistance(new Coordinate(0, 7)));
    }

    @Test
    public void testGivenTwoCoordinatesWhenBetweenThenOk() {
        List<Coordinate> expectation = new ArrayList<Coordinate>();
        expectation.add(new Coordinate(1, 1));
        assertEquals(expectation, new Coordinate(2, 2).between(new Coordinate(0, 0)));
        expectation.clear();
        expectation.add(new Coordinate(2, 2));
        expectation.add(new Coordinate(1, 1));
        assertEquals(expectation, new Coordinate(3, 3).between(new Coordinate(0, 0)));

    }

}