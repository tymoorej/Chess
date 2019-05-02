package Players;

import BoardHelpers.Board;
import BoardHelpers.BoardPosition;
import Enums.XPosition;
import Moves.PieceMover;
import Pieces.Colour;

public class Bot extends Player {

    public Bot() {
        setColour(Colour.BLACK);
    }

    @Override
    public void takeTurn() {
        waitUntilTurn();
        PieceMover.move(Board.getInstance().getSquare(new BoardPosition(XPosition.D,7)),Board.getInstance().getSquare(new BoardPosition(XPosition.D,6)));
        System.out.println("BOT DONE");
    }

}
