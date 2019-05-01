package Pieces;

import Board.BoardPosition;
import Board.Distance2D;

public class Bishop extends Piece {
    private static final int value = 3;
    private static final boolean canJump = false;
    private Colour colour;

    public Bishop(Colour colour) {
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

}
