package NeuralNetworkHelpers;

public class Layer {
    private Node[] nodes;

    public Layer(int size) {
        nodes = new Node[size];

        for (int i = 0; i < size; i++){
            nodes[i] = new Node();
        }

    }

    public Node[] getNodes() {
        return nodes;
    }

    public int getSize() {
        return nodes.length;
    }

    public void print() {
        for (Node node : nodes){
            System.out.println(node.getValue());
        }
    }
}
