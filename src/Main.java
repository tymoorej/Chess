import BoardHelpers.Board;
import GameHandlers.Game;
import NeuralNetworkHelpers.NeuralNetwork;
import NeuralNetworkHelpers.NeuralNetworkFileManager;
import Pieces.Colour;
import Players.Bot;
import Players.Player;
import UI.BoardUIHandler;


import static java.lang.Thread.sleep;

public class Main {

    public static void start(){
        BoardUIHandler.setup();
        try{
            Player player1 = new Bot(Colour.WHITE, 1);
            Player player2 = new Bot(Colour.BLACK, 2);
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
//        start();

        NeuralNetwork neuralNetwork = new NeuralNetwork(0, 1);
        neuralNetwork.randomizeAllWeigths();
//        neuralNetwork.calculateOutputLayer(Board.getInstance());
        NeuralNetworkFileManager.saveNeuralNetwork(1, neuralNetwork);
        NeuralNetwork neuralNetwork2 = NeuralNetworkFileManager.readNeuralNetwork(1);

    }
}
