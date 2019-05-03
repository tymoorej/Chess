package Players;

import BoardHelpers.Board;
import Exceptions.UnexpectedStateException;
import Moves.Move;
import NeuralNetworkHelpers.NeuralNetwork;
import NeuralNetworkHelpers.Node;
import NeuralNetworkHelpers.OutputNodeIndexCombo;
import Pieces.Colour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Bot extends Player {

    private NeuralNetwork neuralNetwork;
    private int id;

    public Bot(Colour colour, int id) {
        int numberOfHiddenLayers = (int) (Math.random() * 20);
        int sizeOfHiddenLayers = (int) (Math.random() * 200);

        this.neuralNetwork = new NeuralNetwork(numberOfHiddenLayers, sizeOfHiddenLayers);
        neuralNetwork.randomizeAllWeigths();

        this.id = id;
        setColour(colour);
        addPlayer(this);
    }

    @Override
    public void makeMove() {
        ArrayList<Move> moves = Move.getAvailableMoves(this.getColour(), true, Board.getInstance());

        neuralNetwork.calculateOutputLayer(Board.getInstance());
        Node[] nodes = neuralNetwork.getOutputLayer().getNodes();
        OutputNodeIndexCombo[] outputNodeIndexCombos = new OutputNodeIndexCombo[nodes.length];

        for (int i = 0; i < Board.xSize * Board.ySize * Board.xSize * Board.ySize; i++){
            outputNodeIndexCombos[i] = new OutputNodeIndexCombo(nodes[i], i);
        }

        Arrays.sort(outputNodeIndexCombos, Collections.reverseOrder());

        Move move;
        for (int i = 0; i < Board.xSize * Board.ySize * Board.xSize * Board.ySize; i++){
            move = outputNodeIndexCombos[i].convertOutputIndexOfNodeToMove();
            if (moves.contains(move)){
                move = moves.get(moves.indexOf(move));
                System.out.println(move.toString() + " : " + outputNodeIndexCombos[i].getNode().getValue());
                move.doMove(true);
                return;
            }
        }

        throw new UnexpectedStateException();

    }

}
