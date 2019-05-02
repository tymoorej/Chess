package Players;

import BoardHelpers.Board;
import BoardHelpers.BoardPosition;
import Enums.XPosition;
import GameHandlers.Game;
import Moves.Move;
import Moves.PieceMover;
import Pieces.Colour;

import java.util.ArrayList;

public class Bot extends Player {

    public Bot() {
        setColour(Colour.BLACK);
    }

    @Override
    public void takeTurn() {
        ArrayList<Move> moves = PieceMover.getAvailableMoves(this);

        if (moves.size() == 0){
            Game.getInstance().setGameOver();
            return;
        }

        Move move = moves.get((int) (Math.random() * moves.size()));

        PieceMover.move(move.getStart(), move.getEnd());
        waitUntilTurnDone();
    }

}
