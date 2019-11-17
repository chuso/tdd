package es.urjccode.mastercloudapps.adcs.draughts.models;

import java.util.ArrayList;
import java.util.List;

class Draught extends Piece {

    Draught(Color color) {
        super(color);
    }

    Coordinate getEatenCoordinate(Coordinate origin, Coordinate target) {
        // TODO
        return null;
    }

    Error isCorrect(Coordinate origin, Coordinate target, PieceProvider pieceProvider) {
        Error error = super.isCorrect(origin, target, pieceProvider);
        if (error != null) {
            return error;
        }
        if (getOpponenCoordinatesInRange(origin, target, pieceProvider).size() > 1) {
            return Error.EATING_TOO_MUCH;
        }
        return null;
    }

    private List<Coordinate> getOpponenCoordinatesInRange(Coordinate origin, Coordinate target, PieceProvider pieceProvider) {
        List<Coordinate> coordinatesBetween = origin.between(target);
        List<Coordinate> opponenCoordinates = new ArrayList<Coordinate>();
        for (Coordinate coordinate : coordinatesBetween) {
            Piece piece = pieceProvider.getPiece(coordinate);
            if (piece != null && piece.getColor() != this.getColor()) {
                opponenCoordinates.add(coordinate);
            }
        }
        return opponenCoordinates;
    }

}