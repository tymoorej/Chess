import GameHandlers.Game;
import Players.Bot;
import Players.Human;
import UI.BoardUIHandler;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        BoardUIHandler.setup();
        Game.getInstance().GameLoop(new Human(), new Bot());
    }
}
