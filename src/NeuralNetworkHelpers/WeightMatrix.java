package NeuralNetworkHelpers;

public class WeightMatrix {
    private int previousLayerSize;
    private int nextLayerSize;
    private double[][] weights;


    public WeightMatrix(int previousLayerSize, int nextLayerSize) {
        this.previousLayerSize = previousLayerSize;
        this.nextLayerSize = nextLayerSize;
        weights = new double[previousLayerSize][nextLayerSize];

        for (int i = 0; i < previousLayerSize; i++){
            for (int j = 0; j < nextLayerSize; j++){
                weights[i][j] = 0;
            }
        }
    }

    public void randomizeWeigths(){
        for (int i = 0; i < previousLayerSize; i++){
            for (int j = 0; j < nextLayerSize; j++){
                weights[i][j] = (Math.random() * 2) - 1;
            }
        }
    }

    public int getPreviousLayerSize() {
        return previousLayerSize;
    }

    public void setPreviousLayerSize(int previousLayerSize) {
        this.previousLayerSize = previousLayerSize;
    }

    public int getNextLayerSize() {
        return nextLayerSize;
    }

    public void setNextLayerSize(int nextLayerSize) {
        this.nextLayerSize = nextLayerSize;
    }

    public double[][] getWeights() {
        return weights;
    }

    public void setWeights(double[][] weights) {
        this.weights = weights;
    }
}
