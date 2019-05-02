import GameHandlers.Game;
import Pieces.Colour;
import Players.Bot;
import Players.Human;
import Players.Player;
import UI.BoardUIHandler;

public class Main {
    public static void main(String[] args) {
        BoardUIHandler.setup();
        try{
            Player player1 = new Human(Colour.WHITE);
            Player player2 = new Bot(Colour.BLACK);
            Player winner = Game.getInstance().GameLoop(player1, player2, 0);
            System.out.println(winner.getColour().name() + " is the winner!");
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            System.exit(0);
        }
    }
}
