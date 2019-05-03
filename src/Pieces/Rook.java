package Pieces;

import BoardHelpers.BoardPosition;
import BoardHelpers.Distance2D;

public class Rook extends Piece implements IHasMoved{
    private static final int value = 4;
    private static final boolean canJump = false;
    private Colour colour;
    private boolean hasMoved;

    public Rook(Colour colour) {
        this.colour = colour;
        hasMoved = false;
    }

    public Rook(Colour colour, boolean hasMoved) {
        this.colour = colour;
        this.hasMoved = hasMoved;
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

        if (distance.getxDistance() * distance.getyDistance() != 0){
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

    @Override
    protected String getPieceName() {
        return "rook";
    }

    @Override
    public String toString() {
        return "Rook{" +
                "colour=" + colour +
                '}';
    }

    @Override
    public Piece getCopy() {
        return new Rook(colour, hasMoved);
    }
}
