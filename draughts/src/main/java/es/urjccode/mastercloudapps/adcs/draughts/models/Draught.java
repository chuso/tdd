package es.urjccode.mastercloudapps.adcs.draughts.models;

class Draught extends Piece {

    Draught(Color color) {
        super(color);
    }

    @Override
    protected Error isCorrectSpecificPiece(Coordinate origin, Coordinate target) {
        return null;
    }
}