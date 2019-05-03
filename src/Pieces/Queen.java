package Pieces;

import BoardHelpers.BoardPosition;
import BoardHelpers.Distance2D;

public class Queen extends Piece {
    private static final int value = 5;
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
        Distance2D distance = start.distanceTo(end);

        if (distance.getxDistance() == 0 && distance.getyDistance() == 0){
            return false;
        }

        if (Math.abs(distance.getxDistance()) == Math.abs(distance.getyDistance())){
            return true;
        }

        if (distance.getxDistance() * distance.getyDistance() == 0){
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
        return "queen";
    }

    @Override
    public String toString() {
        return "Queen{" +
                "colour=" + colour +
                '}';
    }

    @Override
    public Piece getCopy() {
        return new Queen(colour);
    }
}
