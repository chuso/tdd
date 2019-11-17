package es.urjccode.mastercloudapps.adcs.draughts.models;

public class Piece implements Cloneable {

	private Color color;
	private static final int MAX_DISTANCE = 2;

	Piece(Color color) {
		assert color != null;
		this.color = color;
	}

	Error isCorrect(Coordinate origin, Coordinate target, PieceProvider pieceProvider) {
		if (!origin.isDiagonal(target)) {
			return Error.NOT_DIAGONAL;
		}
		if (!pieceProvider.isEmpty(target)) {
			return Error.NOT_EMPTY_TARGET;
		}
		Error specificPieceError = this.isCorrectSpecificPiece(origin, target);
		if (specificPieceError != null) {
			return specificPieceError;
		}
		int distance = origin.diagonalDistance(target);
		if (distance > Piece.MAX_DISTANCE) {
			return Error.BAD_DISTANCE;
		}
		if (distance == Piece.MAX_DISTANCE) {
			if (pieceProvider.getPiece(origin.betweenDiagonal(target)) == null) {
				return Error.EATING_EMPTY;
			}
		}
		return null;
	}

	boolean isLimit(Coordinate coordinate){
		return coordinate.getRow()== 0 && this.getColor() == Color.WHITE ||
		coordinate.getRow()== 7 && this.getColor() == Color.BLACK;
	}

	boolean isAdvanced(Coordinate origin, Coordinate target) {
		assert origin != null;
		assert target != null;
		int difference = origin.getRow() - target.getRow();
		if (color == Color.WHITE) {
			return difference > 0;
		}
		return difference < 0;
	}

	Color getColor() {
		return this.color;
	}

	protected Error isCorrectSpecificPiece(Coordinate origin, Coordinate target) {
		if (!this.isAdvanced(origin, target)) {
			return Error.NOT_ADVANCED;
		}
		return null;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Piece other = (Piece) obj;
        if (color != other.color)
            return false;
        return true;
    }

}