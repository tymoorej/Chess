package Players;

import BoardHelpers.Board;
import Exceptions.UnexpectedStateException;
import Moves.Move;
import NeuralNetworkHelpers.NeuralNetwork;
import NeuralNetworkHelpers.Node;
import NeuralNetworkHelpers.OutputNodeIndexCombo;
import Pieces.Colour;

import java.util.Arrays;
import java.util.Collections;

public class Bot extends Player {

    private NeuralNetwork neuralNetwork;

    public Bot(Colour colour, NeuralNetwork neuralNetwork) {
        this.neuralNetwork = neuralNetwork;
        setColour(colour);
        addPlayer(this);
    }

    @Override
    public void makeMove() {
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

            if (move.getStart().isEmpty()){
                continue;
            }

            if (!getColour().equals(move.getStart().getPiece().getColour())){
                continue;
            }

            if (move.canMove(true, Board.getInstance())){
                move.doMove(true);
                return;
            }
        }

        throw new UnexpectedStateException();

    }

}
