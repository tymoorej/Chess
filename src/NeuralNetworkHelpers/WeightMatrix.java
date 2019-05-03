package NeuralNetworkHelpers;

public class WeightMatrix {
    private Layer previousLayer;
    private Layer nextLayer;
    private double[][] weights;


    public WeightMatrix(Layer previousLayer, Layer nextLayer) {
        this.previousLayer = previousLayer;
        this.nextLayer = nextLayer;
        weights = new double[previousLayer.getSize()][nextLayer.getSize()];

        for (int i = 0; i < previousLayer.getSize(); i++){
            for (int j = 0; j < nextLayer.getSize(); j++){
                weights[i][j] = 0;
            }
        }
    }

    public void randomizeWeigths(){
        for (int i = 0; i < previousLayer.getSize(); i++){
            for (int j = 0; j < nextLayer.getSize(); j++){
                weights[i][j] = (Math.random() * 2) - 1;
            }
        }
    }

    public Layer getPreviousLayer() {
        return previousLayer;
    }

    public void setPreviousLayer(Layer previousLayer) {
        this.previousLayer = previousLayer;
    }

    public Layer getNextLayer() {
        return nextLayer;
    }

    public void setNextLayer(Layer nextLayer) {
        this.nextLayer = nextLayer;
    }

    public double[][] getWeights() {
        return weights;
    }

    public void setWeights(double[][] weights) {
        this.weights = weights;
    }
}
