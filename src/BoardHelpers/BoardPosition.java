package BoardHelpers;

import Enums.XPosition;
import Exceptions.IllegalPositionException;

import java.util.Objects;

public class BoardPosition implements Copyable<BoardPosition>{
    private XPosition xPosition;
    private Integer yPosition;

    public BoardPosition(XPosition xPosition, Integer yPosition) {
        this.xPosition = xPosition;

        if (yPosition < 1 || yPosition > 8){
            throw new IllegalPositionException();
        }

        this.yPosition = yPosition;
    }

    public XPosition getxPosition() {
        return xPosition;
    }

    public void setxPosition(XPosition xPosition) {
        this.xPosition = xPosition;
    }

    public Integer getyPosition() {
        return yPosition;
    }

    public void setyPosition(Integer yPosition) {

        if (yPosition < 1 || yPosition > 8){
            throw new IllegalPositionException();
        }

        this.yPosition = yPosition;
    }

    public Distance2D distanceTo(BoardPosition otherPosition){
        int deltaX = otherPosition.getxPosition().ordinal() - this.getxPosition().ordinal();
        int deltaY = otherPosition.getyPosition() - this.getyPosition();
        return new Distance2D(deltaX, deltaY);
    }

    @Override
    public BoardPosition getCopy() {
        return new BoardPosition(xPosition, yPosition);
    }

    @Override
    public String toString() {
        return xPosition.name() + "-" + yPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BoardPosition)) return false;
        BoardPosition that = (BoardPosition) o;
        return xPosition == that.xPosition &&
                Objects.equals(yPosition, that.yPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xPosition, yPosition);
    }
}
