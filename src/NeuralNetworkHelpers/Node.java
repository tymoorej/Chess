package NeuralNetworkHelpers;

public class Node implements Comparable<Node>{
    private double value;

    public Node() {
        this.value = 0;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double unNormalizedValue) {
        this.value = normalize(unNormalizedValue);
    }

    private double normalize(double unNormalizedValue){
        return 1 / (1 + Math.exp(-1 * unNormalizedValue));
    }

    @Override
    public int compareTo(Node o) {

        if (value > o.getValue()){
            return 1;
        }

        if (value < o.getValue()){
            return -1;
        }

        return 0;
    }
}
