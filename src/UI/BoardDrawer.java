package UI;

import BoardHelpers.Board;
import BoardHelpers.BoardPosition;
import Enums.GameState;
import Enums.XPosition;
import GameHandlers.Game;

import javax.swing.*;
import java.awt.*;

public class BoardDrawer extends JPanel {
    private int totalWidth;
    private int totalHeight;

    private static final int xSections = 100;
    private static final int ySections = 100;
    private int xSectionSize;
    private int ySectionSize;

    private int boardStartX;
    private int boardStartY;
    private int boardWidth;
    private int boardHieght;

    private int boardXSections;
    private int boardYSections;
    private int boardXSectionSize;
    private int boardYSectionSize;

    private BoardDrawableRectangle[][] boardDrawableRectangles;




    public BoardDrawer(BoardDrawableRectangle[][] boardDrawableRectangles) {
        totalHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        totalWidth = Toolkit.getDefaultToolkit().getScreenSize().width;

        boardXSections = Board.xSize;
        boardYSections = Board.ySize;

        xSectionSize = totalWidth / xSections;
        ySectionSize = totalHeight / ySections;
        boardStartX = 10 * xSectionSize;
        boardStartY = 10 * ySectionSize;
        boardWidth = 10 * boardXSections * xSectionSize;
        boardHieght = 10 * boardYSections * ySectionSize;
        boardXSectionSize = boardWidth / boardXSections;
        boardYSectionSize = boardHieght / boardYSections;

        this.boardDrawableRectangles = boardDrawableRectangles;

        Color c;
        for (int i = 0; i < boardXSections; i++) {
            for (int j = 0; j < boardYSections; j++) {
                if ((i + j) % 2 == 1) {
                    c = Color.gray;
                } else {
                    c = Color.WHITE;
                }

                boardDrawableRectangles[i][j] = new BoardDrawableRectangle(boardStartX + i * boardXSectionSize,
                        boardXSectionSize, boardStartX + i * boardXSectionSize + boardXSectionSize - 1,
                        boardStartY + j * boardYSectionSize, boardYSectionSize,
                        boardStartY + j * boardYSectionSize + boardYSectionSize, new BoardPosition(XPosition.values()[i], 8 - j),
                        c);
            }
        }

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(new Color(200, 200, 200));

        for (int i = 0; i < boardXSections; i++){
            for (int j = 0; j < boardYSections; j++){
                boardDrawableRectangles[i][j].draw(g);
            }
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font("Locations", Font.BOLD, 30));

        for (int i = 0; i < boardXSections; i++){
            g.drawString(XPosition.values()[i].name(), boardStartX + i*boardXSectionSize + boardXSectionSize/2,
                    boardStartY + boardHieght + boardYSectionSize/2);
            g.drawString(XPosition.values()[i].name(), boardStartX + i*boardXSectionSize + boardXSectionSize/2,
                    boardStartY - boardYSectionSize/4);
        }

        for (int j = 0; j < boardYSections; j++){
            g.drawString(Integer.toString(j+1), boardStartX - boardXSectionSize/2,
                    boardStartY + boardHieght - boardYSectionSize*j - boardYSectionSize/2);
            g.drawString(Integer.toString(j+1), boardStartX + boardWidth + boardXSectionSize/4,
                    boardStartY + boardHieght - boardYSectionSize*j - boardYSectionSize/2);
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font("Title", Font.BOLD, 50));

        if (Game.getInstance().getState() == GameState.WHITE_TURN){
            g.drawString("White Turn", 20, 60);
        }
        else if(Game.getInstance().getState() == GameState.BLACK_TURN){
            g.drawString("Black Turn", 20, 60);
        }


        for (int i = 0; i < boardXSections; i++){
            for (int j = 0; j < boardYSections; j++){
                boardDrawableRectangles[i][j].drawPiece(g);
            }
        }



    }
}
