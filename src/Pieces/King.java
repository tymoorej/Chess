package Pieces;

import Board.BoardPosition;
import Board.Distance2D;

public class King extends Piece implements IHasMoved{
    private static final int value = 100;
    private static final boolean canJump = false;
    private Colour colour;
    private boolean hasMoved;

    public King(Colour colour) {
        this.colour = colour;
        hasMoved = false;
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

        if (distance.getyDistance() > 1){
            return false;
        }

        if (distance.getxDistance() > 1){
            return false;
        }

        return true;
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
    public boolean hasPieceMoved() {
        return hasMoved;
    }

    @Override
    public void setPieceMoved() {
        hasMoved = true;
    }
}
