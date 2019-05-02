package GameHandlers;

import BoardHelpers.Board;
import BoardHelpers.BoardPosition;
import BoardHelpers.Square;
import Enums.GameState;
import Enums.XPosition;
import Exceptions.UnexpectedStateException;
import Pieces.King;
import Players.Player;

import static java.lang.Thread.sleep;

public class Game {
    private GameState state;
    private boolean gameOver;

    private static Game ourInstance = new Game();

    public static Game getInstance() {
        return ourInstance;
    }

    public Game() {
        state = GameState.WHITE_TURN;
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

    public Player GameLoop(Player player1, Player player2){
        state = GameState.WHITE_TURN;

        while (!gameOver){
            player1.takeTurn();

            if (gameOver){
                break;
            }

            player2.takeTurn();
        }

        return getWinner(player1, player2);

    }

    private Player getWinner(Player player1, Player player2){

        boolean player1HasKing = false;
        boolean player2HasKing = false;

        Square square;
        for (int i = 0; i < Board.xSize; i++) {
            for (int j = 0; j < Board.ySize; j++) {
                square = Board.getInstance().getSquare(new BoardPosition(XPosition.values()[i], j + 1));
                if (square.isEmpty()){
                    continue;
                }
                if (square.getPiece() instanceof King && square.getPiece().getColour().equals(player1.getColour())){
                    player1HasKing = true;
                }
                if (square.getPiece() instanceof King && square.getPiece().getColour().equals(player2.getColour())){
                    player2HasKing = true;
                }
            }
        }

        if (player1HasKing && !player2HasKing){
            return player1;
        }

        if (!player1HasKing && player2HasKing){
            return player2;
        }

        if (!player1HasKing && !player2HasKing){
            throw new UnexpectedStateException();
        }

        return null;
    }

    public void setGameOver(){
        gameOver = true;
    }

}
