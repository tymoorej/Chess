import BoardHelpers.Board;
import GameHandlers.Game;
import GenerationalAlgorithm.GenerationalTrainer;
import NeuralNetworkHelpers.NeuralNetwork;
import NeuralNetworkHelpers.NeuralNetworkFileManager;
import Pieces.Colour;
import Players.Bot;
import Players.Player;
import UI.BoardUIHandler;


import static java.lang.Thread.sleep;

public class Main {

    public static void start(){
//        BoardUIHandler.setup();
        try{
            Player player1 = new Bot(Colour.WHITE, NeuralNetworkFileManager.readNeuralNetwork(1));
            Player player2 = new Bot(Colour.BLACK, NeuralNetworkFileManager.readNeuralNetwork(10));
            Player winner = Game.getInstance().GameLoop(player1, player2, 0, 50000);
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

    public static void train(){
        GenerationalTrainer generationalTrainer = new GenerationalTrainer();
        try {
            generationalTrainer.train(false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        BoardUIHandler.setup();
//        start();
        train();
        System.exit(0);
    }
}
