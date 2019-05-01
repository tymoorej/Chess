package UI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BoardDrawerMouseListner implements MouseListener {
    private BoardDrawableRectangle[][] boardDrawableRectangles;

    public BoardDrawerMouseListner(BoardDrawableRectangle[][] boardDrawableRectangles) {
        this.boardDrawableRectangles = boardDrawableRectangles;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try{
            BoardDrawableRectangle clickedOn = BoardDrawableRectangle.getRectangleClicked(boardDrawableRectangles, e.getX(), e.getY());
            if (clickedOn != null){
                System.out.println(clickedOn.getPosition().getxPosition() + "," + clickedOn.getPosition().getyPosition());
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
}
