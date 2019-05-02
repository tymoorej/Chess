package Pieces;

import BoardHelpers.BoardPosition;
import BoardHelpers.Distance2D;

public class Pawn extends Piece implements IHasMoved{
    private static final int value = 1;
    private static final boolean canJump = false;
    private Colour colour;
    private boolean hasMoved;

    public Pawn(Colour colour) {
        this.colour = colour;
        hasMoved = false;
    }

    public Pawn(Colour colour, boolean hasMoved) {
        this.colour = colour;
        this.hasMoved = hasMoved;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public boolean isMoveValid(BoardPosition start, BoardPosition end) {
        Distance2D distance = orientDistance(start.distanceTo(end));

        if (distance.getyDistance() == 1 && distance.getxDistance() >= -1 && distance.getxDistance() <= 1){
            return true;
        }

        if (!hasMoved && distance.getyDistance() == 2 && distance.getxDistance() == 0){
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
    public boolean hasPieceMoved() {
        return hasMoved;
    }

    private Distance2D orientDistance(Distance2D distance){
        Distance2D orientedDistance = new Distance2D(distance.getxDistance(), distance.getyDistance());
        if (getColour() == Colour.BLACK){
            orientedDistance.setxDistance(-1 * distance.getxDistance());
            orientedDistance.setyDistance(-1 * distance.getyDistance());
        }
        return orientedDistance;
    }

    @Override
    public void setPieceMoved() {
        hasMoved = true;
    }

    @Override
    protected String getPieceName() {
        return "pawn";
    }

    @Override
    public String toString() {
        return "Pawn{" +
                "colour=" + colour +
                '}';
    }

    @Override
    public Piece getCopy() {
        return new Pawn(colour, hasMoved);
    }
}
