package Players;

import GameHandlers.Game;
import Moves.Move;
import Moves.PieceMover;
import Pieces.Colour;

import java.util.ArrayList;

public class Bot extends Player {

    public Bot(Colour colour) {
        setColour(colour);
        addPlayer(this);
    }

    @Override
    public void makeMove() {
        ArrayList<Move> moves = PieceMover.getAvailableMoves(this.getColour());
        Move move = moves.get((int) (Math.random() * moves.size()));
        PieceMover.move(move.getStart(), move.getEnd());
    }

}
