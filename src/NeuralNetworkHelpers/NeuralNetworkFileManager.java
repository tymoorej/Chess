package NeuralNetworkHelpers;


import com.google.gson.Gson;

import java.io.*;


public class NeuralNetworkFileManager {

    public static void saveNeuralNetwork(int id, NeuralNetwork neuralNetwork){
        NeuralNetworkSaveableData neuralNetworkSaveableData = new NeuralNetworkSaveableData(neuralNetwork);

        Gson gson = new Gson();
        String nnstring =  gson.toJson(neuralNetworkSaveableData);

        File file = new File("NeuralNetworks/" + id + ".txt");
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(file);
            writer.print(nnstring);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (writer!=null){
                writer.close();
            }
        }
    }

    public static NeuralNetwork readNeuralNetwork(int id){
        File file = new File("NeuralNetworks/" + id + ".txt");
        int fileLength = (int) file.length();

        byte[] byteArray = new byte[fileLength];

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(byteArray);
            fileInputStream.close();
        }
        catch (Exception e) {

        }

        String data = new String(byteArray);

        Gson gson = new Gson();
        NeuralNetworkSaveableData neuralNetworkSaveableData = gson.fromJson(data, NeuralNetworkSaveableData.class);
        NeuralNetwork neuralNetwork = new NeuralNetwork(neuralNetworkSaveableData.getNumberOfHiddenLayers(),
                neuralNetworkSaveableData.getSizeOfHiddenLayers());
        neuralNetwork.setWeightMatrices(neuralNetworkSaveableData.getWeightMatrices());

        return neuralNetwork;
    }

}
