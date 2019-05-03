import GameHandlers.Game;
import Pieces.Colour;
import Players.Bot;
import Players.Human;
import Players.Player;
import UI.BoardUIHandler;


import static java.lang.Thread.sleep;

public class Main {

    public static void start(){
        BoardUIHandler.setup();
        try{
            Player player1 = new Bot(Colour.WHITE);
            Player player2 = new Bot(Colour.BLACK);
            Player winner = Game.getInstance().GameLoop(player1, player2, 0);
            if (winner == null){
                System.out.println("Stalemate!");
            }
            else{
                System.out.println(winner.getColour().name() + " is the winner!");
            }

            BoardUIHandler.updateBoard();

            while (true){
                sleep(500);
            }

        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        start();
    }
}
