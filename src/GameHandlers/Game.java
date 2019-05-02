package GameHandlers;

import Enums.GameState;
import Players.Player;

public class Game {
    private GameState state;
    private boolean gameOver;

    private static Game ourInstance = new Game();

    public static Game getInstance() {
        return ourInstance;
    }

    public Game() {
        state = GameState.NOT_STARTED;
        gameOver = false;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public void flipState(){
        if (state.equals(GameState.WHITE_TURN)){
            state = GameState.BLACK_TURN;
        }
        else{
            state = GameState.WHITE_TURN;
        }
    }

    public void GameLoop(Player player1, Player player2){
        state = GameState.WHITE_TURN;


        while (!gameOver){
            player1.takeTurn();
            player2.takeTurn();
        }

    }

    public void setGameOver(){
        gameOver = true;
    }

}
