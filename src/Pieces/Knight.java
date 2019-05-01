package Pieces;

import Board.BoardPosition;

public class Knight extends Piece {
    private static final int value = 3;
    private static final boolean canJump = true;
    private Colour colour;

    public Knight(Colour colour) {
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
