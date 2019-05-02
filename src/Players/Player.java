package Players;

import BoardHelpers.Board;
import Enums.GameState;
import Exceptions.AttemptingToPlayWithDeltedPlayerException;
import Exceptions.ColourIsAlreadySetException;
import Exceptions.TooManyPlayersException;
import Exceptions.TooManyPlayersWithTheSameColourException;
import Moves.Move;
import Pieces.Colour;
import GameHandlers.Game;
import UI.BoardUIHandler;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public abstract class Player {
    private Colour colour;
    private boolean valid = true;
    private static ArrayList<Player> players = new ArrayList<>();

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
        if (getColour() != null){
            throw new ColourIsAlreadySetException();
        }
        this.colour = colour;
    }

    public void takeTurn(){

        if (!this.valid){
            throw new AttemptingToPlayWithDeltedPlayerException();
        }

        if (!canMove()){
            return;
        }

        makeMove();
        waitUntilTurnDone();
        BoardUIHandler.updateBoard();
    }

    public boolean canMove(){
        if (Move.getAvailableMoves(this.getColour(), true, Board.getInstance()).size() == 0){
            Game.getInstance().setGameOver();
            return false;
        }

        return true;
    }

    public abstract void makeMove();

    public void waitUntilTurnDone(){
        while(isPlayersTurn()){
            try {
                sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void addPlayer(Player player){

        if (players.size() >= 2){
            throw new TooManyPlayersException();
        }

        if (players.size() == 1 && players.get(0).getColour().equals(player.getColour())){
            throw new TooManyPlayersWithTheSameColourException();
        }

        players.add(player);
    }

    public void deleteInstance(){
        players.remove(this);
        this.valid = false;
    }

    public boolean isInCheck(){
        return Board.getInstance().isColourInCheck(getColour());
    }

}
