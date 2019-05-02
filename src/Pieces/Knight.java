package Pieces;

import BoardHelpers.BoardPosition;
import BoardHelpers.Distance2D;

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
        Distance2D distance = start.distanceTo(end);

        if (Math.abs(distance.getxDistance()) == 2 && Math.abs(distance.getyDistance()) == 1){
            return true;
        }

        if (Math.abs(distance.getyDistance()) == 2 && Math.abs(distance.getxDistance()) == 1){
            return true;
        }

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
    protected String getPieceName() {
        return "knight";
    }

    @Override
    public String toString() {
        return "Knight{" +
                "colour=" + colour +
                '}';
    }

    @Override
    public Piece getCopy() {
        return new Knight(colour);
    }
}
