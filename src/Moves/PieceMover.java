package Moves;

import Board.Board;
import Board.Square;
import Exceptions.IllegalMoveException;
import Pieces.Piece;

public class PieceMover {

    public void move(Board board, Square start, Square end){
        if (!canMove(board, start, end)){
            throw new IllegalMoveException();
        }
    }

    public static boolean canMove(Board board, Square start, Square end){

        if (start.isEmpty()){
            return false;
        }

        Piece piece = start.getPiece();
        if (!piece.isMoveValid(start.getBoardPosition(), end.getBoardPosition())){
            return false;
        }

        return true;
    }

}
