package Players;

import Pieces.Colour;
import UI.BoardUIHandler;

public class Human extends Player{
    public Human() {
        setColour(Colour.WHITE);
    }

    @Override
    public void takeTurn() {
        waitUntilTurn();
        BoardUIHandler.makeHumanTurn(this);
    }
}
