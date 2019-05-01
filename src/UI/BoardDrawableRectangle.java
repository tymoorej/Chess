package UI;

import Board.BoardPosition;

import java.awt.*;

public class BoardDrawableRectangle{
    private int xStart;
    private int xSize;
    private int xEnd;
    private int yStart;
    private int ySize;
    private int yEnd;

    private BoardPosition position;
    private Color color;

    public BoardDrawableRectangle(int xStart, int xSize, int xEnd, int yStart, int ySize, int yEnd,
                                  BoardPosition position, Color color) {
        this.xStart = xStart;
        this.xSize = xSize;
        this.xEnd = xEnd;
        this.yStart = yStart;
        this.ySize = ySize;
        this.yEnd = yEnd;
        this.position = position;
        this.color = color;
    }

    public void draw(Graphics g){
        g.setColor(color);
        g.fillRect(xStart, yStart, xSize, ySize);
    }

    public void displayLabel(Graphics g){
        g.drawString(position.getxPosition().name() + "-" + position.getyPosition(),
                xStart + xSize/2, yStart + ySize/2);
    }

    public void drawPiece(Graphics g){
        g.drawString(position.getxPosition().name() + "-" + position.getyPosition(),
                xStart + xSize/2, yStart + ySize/2);
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}