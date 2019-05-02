package UI;

import BoardHelpers.Board;
import BoardHelpers.Square;
import Moves.Move;
import Players.Human;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BoardUIHandler {

    private static JFrame jFrame;
    private static BoardDrawableRectangle[][] boardDrawableRectangles;
    private static Square squareSelected = null;
    private static BoardDrawer boardDrawer;
    private static MouseListener humanMouseListner = null;

    public static void setup(){
        jFrame = new JFrame("Chess");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setupBoard();
    }

    public static void setupBoard(){
        boardDrawableRectangles = new BoardDrawableRectangle[Board.xSize][Board.ySize];

        boardDrawer = new BoardDrawer(boardDrawableRectangles);
        jFrame.add(boardDrawer);
        jFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height);
        jFrame.setVisible(true);
    }

    public static void updateBoard(){
        jFrame.repaint();
    }

    private static void removeHighlights(){
        for (int i = 0; i < boardDrawableRectangles.length; i++) {
            for (int j = 0; j < boardDrawableRectangles[i].length; j++) {
                boardDrawableRectangles[i][j].unHighlight();
            }
        }
    }

    public static void makeHumanTurn(Human human){

        updateBoard();

        if (humanMouseListner != null){
            boardDrawer.removeMouseListener(humanMouseListner);
        }
        createMouseListner(human);
        boardDrawer.addMouseListener(humanMouseListner);
    }

    private static void createMouseListner(Human human) {
        humanMouseListner = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try{
                    BoardDrawableRectangle clickedOn = BoardDrawableRectangle.getRectangleClicked(boardDrawableRectangles, e.getX(), e.getY());
                    if (clickedOn != null && human.isPlayersTurn()){
                        removeHighlights();

                        Square square = Board.getInstance().getSquare(clickedOn.getPosition());

                        if (squareSelected == null){

                            if (square.isEmpty()){
                                return;
                            }

                            if (!square.getPiece().getColour().equals(human.getColour())){
                                return;
                            }

                            clickedOn.highlight(boardDrawableRectangles);
                            squareSelected = square;
                        }
                        else{
                            Move move = new Move(squareSelected, square);
                            if (move.canMove(true, Board.getInstance())){
                                move.doMove(true);
                            }
                            squareSelected = null;
                        }
                        updateBoard();
                    }
                }
                catch (Exception ex){

                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
    }
}
