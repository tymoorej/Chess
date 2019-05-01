import Enums.GameState;

public class Game {
    private GameState state;

    private static Game ourInstance = new Game();

    public static Game getInstance() {
        return ourInstance;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }
}
