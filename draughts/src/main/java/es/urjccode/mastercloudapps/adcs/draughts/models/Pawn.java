package es.urjccode.mastercloudapps.adcs.draughts.models;

class Pawn extends Piece {

    private static final int MAX_DISTANCE = 2;

    Pawn(Color color) {
        super(color);
    }

    Error isCorrect(Coordinate origin, Coordinate target, PieceProvider pieceProvider) {
        Error error = super.isCorrect(origin, target, pieceProvider);
        if (error != null) {
            return error;
        }
		if (!this.isAdvanced(origin, target)) {
			return Error.NOT_ADVANCED;
        }
        int distance = origin.diagonalDistance(target);
		if (distance > Pawn.MAX_DISTANCE) {
			return Error.BAD_DISTANCE;
		}
		return null;
    }

    boolean isAdvanced(Coordinate origin, Coordinate target) {
        assert origin != null;
        assert target != null;
        int difference = origin.getRow() - target.getRow();
        if (getColor() == Color.WHITE) {
            return difference > 0;
        }
        return difference < 0;
    }

}