package Players;

import Pieces.Colour;
import UI.BoardUIHandler;

public class Human extends Player{
    public Human(Colour colour) {
        setColour(colour);
        addPlayer(this);
    }

    @Override
    public void makeMove() {
        BoardUIHandler.makeHumanTurn(this);
    }
}
