package Players;

import GameHandlers.Game;
import Moves.PieceMover;
import Pieces.Colour;
import UI.BoardUIHandler;

public class Human extends Player{
    public Human() {
        setColour(Colour.WHITE);
    }

    @Override
    public void takeTurn() {
        if (PieceMover.getAvailableMoves(this).size() == 0){
            Game.getInstance().setGameOver();
            return;
        }

        BoardUIHandler.makeHumanTurn(this);
        waitUntilTurnDone();
    }
}
