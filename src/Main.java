import Board.Board;
import UI.UISetup;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        Board board = new Board();

        UISetup.setup(board);
    }
}
