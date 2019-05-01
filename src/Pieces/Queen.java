package Pieces;

import Board.BoardPosition;

public class Queen extends Piece {
    private static final int value = 9;
    private static final boolean canJump = false;
    private Colour colour;

    public Queen(Colour colour) {
        this.colour = colour;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public boolean isMoveValid(BoardPosition start, BoardPosition end) {
        return false;
    }

    @Override
    public boolean canJumpOverPieces() {
        return canJump;
    }

    @Override
    public Colour getColour() {
        return colour;
    }

    @Override
    public void draw() {

    }
}
