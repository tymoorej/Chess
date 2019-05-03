package NeuralNetworkHelpers;

import BoardHelpers.Board;
import BoardHelpers.BoardPosition;
import BoardHelpers.Square;
import Enums.XPosition;
import Moves.Move;

public class NeuralNetwork {
    private static final int inputNodes = (Board.ySize * Board.ySize);
    private static final int outputNodes = (Board.ySize * Board.ySize)*(Board.ySize * Board.ySize);
    private static final int numberOfHiddenLayers = 10;
    private static int numberOfWeightMatricies;
    private static int numberOfLayers;
    private static final int sizeOfHiddenLayers = 150;

    private Layer[] layers;
    private WeightMatrix[] weigthMatricies;

    public NeuralNetwork() {
        numberOfWeightMatricies = numberOfHiddenLayers + 1;
        numberOfLayers = numberOfHiddenLayers + 2;
        layers = new Layer[numberOfLayers];
        weigthMatricies = new WeightMatrix[numberOfWeightMatricies];

        layers[0] = new Layer(inputNodes);

        for (int i = 1; i < numberOfLayers - 1; i++){
            layers[i] = new Layer(sizeOfHiddenLayers);
        }

        layers[numberOfLayers - 1] = new Layer(outputNodes);

        for (int i = 0; i < numberOfWeightMatricies; i++){
            weigthMatricies[i] = new WeightMatrix(layers[i], layers[i+1]);
            weigthMatricies[i].randomizeWeigths();
        }
    }

    private void loadInputLayer(Board board){
        Square[][] squares = board.getSquares();

        Square square;
        for (int i = 0; i < Board.xSize; i++){
            for (int j = 0; j < Board.ySize; j++){
                square = squares[i][j];

                if (square.isEmpty()){
                    layers[0].getNodes()[i*Board.xSize + j].setValue(0);
                }
                else{
                    layers[0].getNodes()[i*Board.xSize + j].setValue(square.getPiece().getOrientedValue());
                }
            }
        }

    }

    public void calculateOutputLayer(Board board){
        loadInputLayer(board);

        for (int i = 1; i < numberOfLayers; i++){
            layers[i] = calculateLayer(weigthMatricies[i-1]);
        }
    }

    private Layer calculateLayer(WeightMatrix weightMatrix){
        Layer nextLayer = weightMatrix.getNextLayer();
        Layer previousLayer = weightMatrix.getPreviousLayer();

        double temp;
        for (int i = 0; i < nextLayer.getSize(); i++){
            temp = 0;
            for (int j = 0; j < previousLayer.getSize(); j++){
                temp += previousLayer.getNodes()[j].getValue() * weightMatrix.getWeights()[j][i];
            }
            nextLayer.getNodes()[i].setValue(temp);
        }

        return nextLayer;
    }

    public Layer getOutputLayer(){
        return layers[numberOfLayers-1];
    }


    public Layer getLayer(int i){
        return layers[i];
    }
}
