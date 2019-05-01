package UI;

import Board.Board;
import javax.swing.*;
import java.awt.*;

public class UISetup {

    private static JFrame jFrame;
    private static BoardDrawableRectangle[][] boardDrawableRectangles;
    public static void setup(Board board){
        jFrame = new JFrame("Chess");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setupBoard(board);
    }

    public static void setupBoard(Board board){
        boardDrawableRectangles = new BoardDrawableRectangle[Board.xSize][Board.ySize];

        BoardDrawer boardDrawer = new BoardDrawer(boardDrawableRectangles, board);
        jFrame.add(boardDrawer);
        jFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height);
        jFrame.setVisible(true);

        boardDrawer.addMouseListener(new BoardDrawerMouseListner(boardDrawableRectangles));
    }

    public static void updateBoard(){
        jFrame.repaint();
    }
}
