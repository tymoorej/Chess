package NeuralNetworkHelpers;

import BoardHelpers.Board;
import BoardHelpers.Square;

public class NeuralNetwork {
    private static final int inputNodes = (Board.ySize * Board.ySize);
    private static final int outputNodes = (Board.ySize * Board.ySize)*(Board.ySize * Board.ySize);
    private int numberOfWeightMatrices;
    private int numberOfLayers;
    private int numberOfHiddenLayers;
    private int sizeOfHiddenLayers;

    private Layer[] layers;
    private WeightMatrix[] weightMatrices;

    public NeuralNetwork(int numberOfHiddenLayers, int sizeOfHiddenLayers) {
        this.numberOfHiddenLayers = numberOfHiddenLayers;
        this.sizeOfHiddenLayers = sizeOfHiddenLayers;
        numberOfWeightMatrices = numberOfHiddenLayers + 1;
        numberOfLayers = numberOfHiddenLayers + 2;
        layers = new Layer[numberOfLayers];
        weightMatrices = new WeightMatrix[numberOfWeightMatrices];

        layers[0] = new Layer(inputNodes);

        for (int i = 1; i < numberOfLayers - 1; i++){
            layers[i] = new Layer(sizeOfHiddenLayers);
        }

        layers[numberOfLayers - 1] = new Layer(outputNodes);

        for (int i = 0; i < numberOfWeightMatrices; i++){
            weightMatrices[i] = new WeightMatrix(layers[i], layers[i+1]);
        }
    }

    public void randomizeAllWeigths(){
        for (int i = 0; i < numberOfWeightMatrices; i++){
            weightMatrices[i].randomizeWeigths();
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
            layers[i] = calculateLayer(weightMatrices[i-1]);
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

    public WeightMatrix[] getWeightMatrices() {
        return weightMatrices;
    }

    public void setWeightMatrices(WeightMatrix[] weightMatrices) {
        this.weightMatrices = weightMatrices;
    }

    public int getNumberOfHiddenLayers() {
        return numberOfHiddenLayers;
    }

    public int getSizeOfHiddenLayers() {
        return sizeOfHiddenLayers;
    }
}
