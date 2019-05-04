package UI;

import BoardHelpers.Board;
import BoardHelpers.Square;
import BoardHelpers.BoardPosition;
import Moves.Move;
import Pieces.Piece;

import java.awt.*;

public class BoardDrawableRectangle{
    private int xStart;
    private int xSize;
    private int xEnd;
    private int yStart;
    private int ySize;
    private int yEnd;

    private BoardPosition position;
    private Color innerColor;
    private Color outerColor;

    public BoardDrawableRectangle(int xStart, int xSize, int xEnd, int yStart, int ySize, int yEnd,
                                  BoardPosition position, Color innerColor) {
        this.xStart = xStart;
        this.xSize = xSize;
        this.xEnd = xEnd;
        this.yStart = yStart;
        this.ySize = ySize;
        this.yEnd = yEnd;
        this.position = position;
        this.innerColor = innerColor;
        this.outerColor = innerColor;
    }

    public void draw(Graphics graphics){

        graphics.setColor(outerColor);
        graphics.fillRect(xStart, yStart, xSize, ySize);

        graphics.setColor(innerColor);
        graphics.fillRect(xStart + 5, yStart + 5, xSize - 10, ySize - 10);
    }

    public void displayLabel(Graphics graphics){
        graphics.drawString(position.getxPosition().name() + "-" + position.getyPosition(),
                xStart + xSize/2, yStart + ySize/2);
    }

    public void drawPiece(Graphics graphics){
        Square square = Board.getInstance().getSquare(position);
        if (square == null){
            return;
        }
        Piece piece = square.getPiece();
        if (!square.isEmpty()){
            Image image = piece.getImage();
            if (image != null){
                graphics.drawImage(image,xStart, yStart, xSize, ySize, null);
            }
        }
    }

    public void highlight(BoardDrawableRectangle[][] boardDrawableRectangles){
        outerColor = Color.RED;
        showPossibilites(boardDrawableRectangles);
    }

    public void subHighlight(){
        outerColor = Color.GREEN;
    }

    private void showPossibilites(BoardDrawableRectangle[][] boardDrawableRectangles) {
        Square square = Board.getInstance().getSquare(position);

        if (square.isEmpty()){
            return;
        }
        BoardDrawableRectangle currentRectangle;
        Square currentSquare;
        Move move;
        for (int i = 0; i < boardDrawableRectangles.length; i++) {
            for (int j = 0; j < boardDrawableRectangles[i].length; j++) {
                currentRectangle = boardDrawableRectangles[i][j];
                currentSquare =  Board.getInstance().getSquare(currentRectangle.position);

                move = new Move(square, currentSquare);
                if (move.canMove(true, Board.getInstance())){
                    currentRectangle.subHighlight();
                }
            }
        }
    }

    public void unHighlight() {
        outerColor = innerColor;
    }

    public static BoardDrawableRectangle getRectangleClicked(BoardDrawableRectangle[][] boardDrawableRectangles, int xPos, int yPos){
        BoardDrawableRectangle current;
        for (int i = 0; i < boardDrawableRectangles.length; i++){
            for (int j = 0; j < boardDrawableRectangles[i].length; j++){
                current = boardDrawableRectangles[i][j];

                if (xPos >= current.xStart && xPos <= current.xEnd && yPos >= current.yStart && yPos <= current.yEnd){
                    return current;
                }

            }
        }

        return null;
    }


    public int getxStart() {
        return xStart;
    }

    public void setxStart(int xStart) {
        this.xStart = xStart;
    }

    public int getxSize() {
        return xSize;
    }

    public void setxSize(int xSize) {
        this.xSize = xSize;
    }

    public int getxEnd() {
        return xEnd;
    }

    public void setxEnd(int xEnd) {
        this.xEnd = xEnd;
    }

    public int getyStart() {
        return yStart;
    }

    public void setyStart(int yStart) {
        this.yStart = yStart;
    }

    public int getySize() {
        return ySize;
    }

    public void setySize(int ySize) {
        this.ySize = ySize;
    }

    public int getyEnd() {
        return yEnd;
    }

    public void setyEnd(int yEnd) {
        this.yEnd = yEnd;
    }

    public BoardPosition getPosition() {
        return position;
    }

    public void setPosition(BoardPosition position) {
        this.position = position;
    }

    public Color getInnerColor() {
        return innerColor;
    }

    public void setInnerColor(Color innerColor) {
        this.innerColor = innerColor;
    }

}