package NeuralNetworkHelpers;

import BoardHelpers.BoardPosition;
import BoardHelpers.Square;
import Enums.XPosition;
import Moves.Move;

public class OutputNodeIndexCombo implements Comparable<OutputNodeIndexCombo>{
    private Node node;
    private int index;

    public OutputNodeIndexCombo(Node node, int index) {
        this.node = node;
        this.index = index;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public int compareTo(OutputNodeIndexCombo o) {
        return node.compareTo(o.node);
    }

    public Move convertOutputIndexOfNodeToMove(){
        if (index < 0 || index > 4095){
            return null;
        }

        int startIdx = index / 64;

        int startX = startIdx / 8;
        int startY = startIdx % 8;

        int endIdx = index % 64;
        int endX = endIdx / 8;
        int endY = endIdx % 8;

        Square start = new Square(new BoardPosition(XPosition.values()[startX], startY + 1));
        Square end = new Square(new BoardPosition(XPosition.values()[endX], endY + 1));

        return new Move(start, end);

    }
}
