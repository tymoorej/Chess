package Board;

import Enums.XPosition;
import Exceptions.IllegalPositionException;

public class BoardPosition {
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
}
