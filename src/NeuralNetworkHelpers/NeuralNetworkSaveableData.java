package NeuralNetworkHelpers;

public class NeuralNetworkSaveableData {
    private int numberOfHiddenLayers;
    private int sizeOfHiddenLayers;
    private WeightMatrix[] weightMatrices;

    public NeuralNetworkSaveableData() {
    }

    public NeuralNetworkSaveableData(NeuralNetwork neuralNetwork) {
        this.numberOfHiddenLayers = neuralNetwork.getNumberOfHiddenLayers();
        this.sizeOfHiddenLayers = neuralNetwork.getSizeOfHiddenLayers();
        this.weightMatrices = neuralNetwork.getWeightMatrices();
    }

    public NeuralNetworkSaveableData(int numberOfHiddenLayers, int sizeOfHiddenLayers, WeightMatrix[] weightMatrices) {
        this.numberOfHiddenLayers = numberOfHiddenLayers;
        this.sizeOfHiddenLayers = sizeOfHiddenLayers;
        this.weightMatrices = weightMatrices;
    }

    public int getNumberOfHiddenLayers() {
        return numberOfHiddenLayers;
    }

    public void setNumberOfHiddenLayers(int numberOfHiddenLayers) {
        this.numberOfHiddenLayers = numberOfHiddenLayers;
    }

    public int getSizeOfHiddenLayers() {
        return sizeOfHiddenLayers;
    }

    public void setSizeOfHiddenLayers(int sizeOfHiddenLayers) {
        this.sizeOfHiddenLayers = sizeOfHiddenLayers;
    }

    public WeightMatrix[] getWeightMatrices() {
        return weightMatrices;
    }

    public void setWeightMatrices(WeightMatrix[] weightMatrices) {
        this.weightMatrices = weightMatrices;
    }
}
