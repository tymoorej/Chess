import GameHandlers.Game;
import Players.Bot;
import Players.Human;
import Players.Player;
import UI.BoardUIHandler;

public class Main {
    public static void main(String[] args) {
        BoardUIHandler.setup();
        Player winner = Game.getInstance().GameLoop(new Human(), new Bot());
        System.out.println(winner.toString());
        System.exit(0);
    }
}
