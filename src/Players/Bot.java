package Players;

import BoardHelpers.Board;
import Moves.Move;
import Pieces.Colour;

import java.util.ArrayList;

public class Bot extends Player {

    public Bot(Colour colour) {
        setColour(colour);
        addPlayer(this);
    }

    @Override
    public void makeMove() {
        ArrayList<Move> moves = Move.getAvailableMoves(this.getColour(), true, Board.getInstance());
        Move move = moves.get((int) (Math.random() * moves.size()));
        move.doMove(true);
    }

}
