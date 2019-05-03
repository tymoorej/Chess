package GameHandlers;

import Enums.GameState;
import Pieces.Colour;
import Players.Player;

import static java.lang.Thread.sleep;

public class Game {
    private GameState state;
    private boolean gameOver;
    private Colour winningColour;
    private int round;
    private int lastCaptureRound;

    private static Game ourInstance = new Game();

    public static Game getInstance() {
        return ourInstance;
    }

    public Game() {
        state = GameState.WHITE_TURN;
        round = 0;
        lastCaptureRound = 0;
        gameOver = false;
        winningColour = null;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getLastCaptureRound() {
        return lastCaptureRound;
    }

    public void setLastCaptureRound(int lastCaptureRound) {
        this.lastCaptureRound = lastCaptureRound;
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

    public Player GameLoop(Player player1, Player player2, int delay) throws InterruptedException {
        state = GameState.WHITE_TURN;

        round = 0;
        lastCaptureRound = 0;

        while (!gameOver){
            player1.takeTurn();

            if (gameOver){
                break;
            }

            sleep(delay);

            player2.takeTurn();

            sleep(delay);
            round ++;

            if (round - lastCaptureRound > 50){
                gameOver = true;
            }
        }

        return getWinner(player1, player2);

    }

    public Player getWinner(Player player1, Player player2){
        if (player1.isInCheck()){
            winningColour = player2.getColour();
            return player2;
        }

        if (player2.isInCheck()){
            winningColour = player1.getColour();
            return player1;
        }

        return null;
    }

    public void setGameOver(){
        gameOver = true;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Colour getWinningColour() {
        return winningColour;
    }
}
