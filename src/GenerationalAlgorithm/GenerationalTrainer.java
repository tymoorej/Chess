package GenerationalAlgorithm;

import Exceptions.ReadingNeuralNetworksAllignmentException;
import GameHandlers.Game;
import NeuralNetworkHelpers.NeuralNetwork;
import NeuralNetworkHelpers.NeuralNetworkFileManager;
import NeuralNetworkHelpers.WeightMatrix;
import Pieces.Colour;
import Players.Bot;

import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;

public class GenerationalTrainer {
    private static final int numberOfGenerations = 50;
    private static final int numberOfNNPerGeneration = 10;
    private static final int numberOfHiddenLayers = 10;
    private static final int sizeOfHiddenLayer = 150;
    private static final int generationCarryOver = 5;
    private static final int generationImmigrants = 5;

    private int generation;
    private IDScoreCombo[] idScoreCombos;
    private NeuralNetwork[] neuralNetworks;

    public GenerationalTrainer() {
        generation = 0;
    }

    public void train(boolean startOver) throws InterruptedException {
        if (startOver){
            createFullRandomNewGeneration();
        }

        readNeuralNetworks();

        while (generation < numberOfGenerations){

            idScoreCombos = new IDScoreCombo[numberOfNNPerGeneration];
            for (int i = 0; i < numberOfNNPerGeneration; i++){
                idScoreCombos[i] = new IDScoreCombo(i);
            }

            playAndScore();
            generateReport();
            createNewGeneration();
            generation ++;
        }

        writeNeuralNetworks();
    }

    private void readNeuralNetworks() {
        neuralNetworks = new NeuralNetwork[numberOfNNPerGeneration];
        for (int i = 0; i < numberOfNNPerGeneration; i++){
            neuralNetworks[i] = NeuralNetworkFileManager.readNeuralNetwork(i);
            if (neuralNetworks[i].getId() != i){
                throw new ReadingNeuralNetworksAllignmentException();
            }
        }
    }
    private void writeNeuralNetworks() {
        for (int i = 0; i < numberOfNNPerGeneration; i++){
            NeuralNetworkFileManager.saveNeuralNetwork(neuralNetworks[i]);
        }
    }



    private void playAndScore() throws InterruptedException {

        Bot winner;
        Bot currentBot;
        Bot opposingBot;

        for (int i = 0; i < numberOfNNPerGeneration; i++){
            currentBot = new Bot(Colour.WHITE, neuralNetworks[i]);
            for (int j = 0; j < numberOfNNPerGeneration; j++){
                if (i == j){
                    continue;
                }
                System.out.println("Generation: " + generation + ". Bot " + i + " vs Bot " + j);
                opposingBot = new Bot(Colour.BLACK, neuralNetworks[j]);
                winner = (Bot) Game.getInstance().GameLoop(currentBot, opposingBot, 0, 0);
                if (winner == currentBot){
                    idScoreCombos[i].addWin();
                    idScoreCombos[j].addLoss();
                }
                else if (winner == opposingBot){
                    idScoreCombos[j].addWin();
                    idScoreCombos[i].addLoss();
                }
                else{
                    idScoreCombos[i].addTie();
                    idScoreCombos[j].addTie();
                }

                opposingBot.deleteInstance();
            }
            currentBot.deleteInstance();
        }
    }

    private void generateReport() {
        File file = new File("GenerationalReports/generation-" + generation + ".txt");
        PrintWriter writer = null;

        StringBuilder s = new StringBuilder("");

        for (int i = 0; i < numberOfNNPerGeneration; i++){
            s.append("Bot " + i + ": " + idScoreCombos[i].getWins() + " wins, " +
                    idScoreCombos[i].getLosses() + " losses, " + idScoreCombos[i].getTies() + " ties. Total Score = "
                    + idScoreCombos[i].getScore() + "\n");
        }

        s.append("\n\n Top Scores: \n");

        int[] topBots = getIndexesOfTop5Nodes();

        for (int i = 0; i < topBots.length; i++){
            s.append("Bot " + topBots[i] + ": " + idScoreCombos[topBots[i]].getScore() + "\n");
        }

        try {
            writer = new PrintWriter(file);
            writer.print(s.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (writer!=null){
                writer.close();
            }
        }

    }

    private void createNewGeneration() {
        NeuralNetwork[] nextGeneration = new NeuralNetwork[numberOfNNPerGeneration];

        for (int i = 0; i < numberOfNNPerGeneration; i++){
            nextGeneration[i] = null;
        }

        int[] NNsToKeep = getIndexesOfTop5Nodes();

        for (int i = 0; i < generationCarryOver; i++){
            nextGeneration[NNsToKeep[i]] = neuralNetworks[NNsToKeep[i]];
        }

        int immigrants = 0;
        for (int i = 0; (i < numberOfNNPerGeneration) && immigrants < generationImmigrants; i++){
            if (nextGeneration[i] != null){
                continue;
            }

            immigrants++;
            nextGeneration[i] = new NeuralNetwork(numberOfHiddenLayers, sizeOfHiddenLayer, i);
            nextGeneration[i].randomizeAllWeigths();
        }

        for (int i = 0; i < numberOfNNPerGeneration; i++){
            if (nextGeneration[i] != null){
                continue;
            }
            nextGeneration[i] = getMutatedNeuralNetwork(neuralNetworks, NNsToKeep, i);
        }

        neuralNetworks = nextGeneration;
    }

    private NeuralNetwork getMutatedNeuralNetwork(NeuralNetwork[] neuralNetworks, int[] nNsToKeep, int idx) {
        NeuralNetwork[] neuralNetworksToBreed = new NeuralNetwork[generationCarryOver];

        for (int i = 0; i < generationCarryOver; i++){
            neuralNetworksToBreed[i] = neuralNetworks[nNsToKeep[i]];
        }

        NeuralNetwork neuralNetwork = new NeuralNetwork(numberOfHiddenLayers, sizeOfHiddenLayer, idx);

        WeightMatrix[] combinedWeightMatrices = new WeightMatrix[numberOfHiddenLayers + 1];

        WeightMatrix[] weightMatrices;

        for (int i = 0; i < numberOfHiddenLayers + 1; i++){
            weightMatrices = new WeightMatrix[generationCarryOver];

            for (int j = 0; j < generationCarryOver; j++){
                weightMatrices[j] = neuralNetworksToBreed[j].getWeightMatrices()[i];
            }

            combinedWeightMatrices[i] = getCombinedWeightMatrix(weightMatrices);
        }

        neuralNetwork.setWeightMatrices(combinedWeightMatrices);

        return neuralNetwork;
    }

    private WeightMatrix getCombinedWeightMatrix(WeightMatrix[] weightMatrices) {
        WeightMatrix combinedWeightMatrix = new WeightMatrix(weightMatrices[0].getPreviousLayerSize(), weightMatrices[0].getNextLayerSize());
        double[][] combinedWeights = new double[weightMatrices[0].getPreviousLayerSize()][weightMatrices[0].getNextLayerSize()];

        for (int i = 0; i < weightMatrices[0].getPreviousLayerSize(); i++){
            for (int j = 0; j < weightMatrices[0].getNextLayerSize(); j++){
                double[] weights = new double[generationCarryOver];

                for (int g = 0; g < generationCarryOver; g++){
                    weights[g] = weightMatrices[g].getWeights()[i][j];
                }

                combinedWeights[i][j] = getRandomCombination(weights);

            }
        }

        combinedWeightMatrix.setWeights(combinedWeights);

        return combinedWeightMatrix;
    }

    public double getRandomCombination(double[] parents){
        int size = parents.length;
        double value = 0.0;

        double randomFactor = (Math.random() * 2) - 1;
        double randomFactorModifier = 0;

        double[] modifiers = new double[size];

        for (int i = 0; i < size; i++){
            modifiers[i] = Math.random() * (1.0 / size);
            randomFactorModifier += modifiers[i];
        }

        for (int i = 0; i < size; i++){
            value += parents[i] * ((1.0 / size) - modifiers[i]);
        }

        value += randomFactor * randomFactorModifier;

        return value;
    }

    private int[] getIndexesOfTop5Nodes(){
        int[] topIndexes = new int[generationCarryOver];
        IDScoreCombo[] sortedIDScoreCombos = new IDScoreCombo[numberOfNNPerGeneration];

        System.arraycopy(idScoreCombos, 0, sortedIDScoreCombos, 0, numberOfNNPerGeneration);

        Arrays.sort(sortedIDScoreCombos, Collections.reverseOrder());

        for (int i = 0; i < generationCarryOver; i++){
            topIndexes[i] = sortedIDScoreCombos[i].getId();
            System.out.println(topIndexes[i]);
        }

        return topIndexes;
    }

    private void createFullRandomNewGeneration(){
        generation = 0;
        for (int i = 0; i < numberOfNNPerGeneration; i++){
            NeuralNetwork neuralNetwork = new NeuralNetwork(numberOfHiddenLayers, sizeOfHiddenLayer, i);
            neuralNetwork.randomizeAllWeigths();
            NeuralNetworkFileManager.saveNeuralNetwork(neuralNetwork);
        }
    }



}
