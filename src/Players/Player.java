package Players;

import Enums.GameState;
import Pieces.Colour;
import GameHandlers.Game;

import static java.lang.Thread.sleep;

public abstract class Player {
    private Colour colour;

    public boolean isPlayersTurn(){
        Game game = Game.getInstance();
        if (game.getState().equals(GameState.WHITE_TURN) && colour.equals(Colour.WHITE)){
            return true;
        }

        if (game.getState().equals(GameState.BLACK_TURN) && colour.equals(Colour.BLACK)){
            return true;
        }

        return false;

    }

    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

    public abstract void takeTurn();

    public void waitUntilTurn(){
        while(!isPlayersTurn()){
            try {
                sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
