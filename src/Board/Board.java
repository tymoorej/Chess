package Board;

import Exceptions.IllegalMoveException;
import Pieces.Piece;
import UI.IDrawable;

public class Board implements IDrawable {
    public static final int xSize = 8;
    public static final int ySize = 8;
    private Square[][] squares;

    public Board() {
        squares = new Square[xSize][ySize];

        for (int i = 0; i < xSize; i++){
            for (int j = 0; j < ySize; j++){
                squares[i][j] = new Square(new BoardPosition(XPosition.values()[i], j + 1));
            }
        }
    }

    public boolean canMove(Piece piece, Square start, Square end){
        if (!piece.isMoveValid(start.getBoardPosition(), end.getBoardPosition())){
            return false;
        }

        if (piece.canJumpOverPieces()){

        }
        else{

        }

        return true;
    }

    public void move(Piece piece, Square start, Square end){
        if (!canMove(piece, start, end)){
            throw new IllegalMoveException();
        }
    }

    public Square[][] getSquares(){
        return squares;
    }

    @Override
    public void draw() {

    }
}
