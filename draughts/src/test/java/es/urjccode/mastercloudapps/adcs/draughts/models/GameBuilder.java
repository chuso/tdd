package es.urjccode.mastercloudapps.adcs.draughts.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class GameBuilder {

    private List<String> strings;
    private Turn turn;
    private Map<Character, Piece> piecePrototype;

    GameBuilder() {
        this.strings = new ArrayList<String>();
        this.turn = new Turn();
        this.piecePrototype = new HashMap<Character, Piece>();
        this.piecePrototype.put('b', new Piece(Color.WHITE));
        this.piecePrototype.put('B', new Draught(Color.WHITE));
        this.piecePrototype.put('n', new Piece(Color.BLACK));
        this.piecePrototype.put('N', new Draught(Color.WHITE));
    }

    public GameBuilder row(String string) {
        this.strings.add(string);
        return this;
    }

    public GameBuilder changeTurn() {
        this.turn.change();
        return this;
    }

    public Game build() {
        Board board = new Board();
        Piece piece;
        for (int i = 0; i < this.strings.size(); i++) {
            for (int j = 0; j < this.strings.get(i).length(); j++) {
                char character = this.strings.get(i).charAt(j);
                piece = this.buildPiece(character);
                if (piece != null) {
                    board.put(new Coordinate(i, j), piece);
                }
            }
        }
        return new Game(board, turn);
    }

    private Piece buildPiece(char character) {
        if (character == ' ') {
            return null;
        }
        try {
            return (Piece)this.piecePrototype.get(character).clone();
        } catch (CloneNotSupportedException exception) {
            return null;
        }
    }

}
