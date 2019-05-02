package Moves;

import BoardHelpers.Board;
import BoardHelpers.BoardPosition;
import BoardHelpers.Distance2D;
import BoardHelpers.Square;
import Enums.XPosition;
import Exceptions.IllegalMoveException;
import GameHandlers.Game;
import Pieces.IHasMoved;
import Pieces.King;
import Pieces.Pawn;
import Pieces.Piece;


public class PieceMover {

    public static void move(Square start, Square end){
        if (!canMove(start, end)){
            throw new IllegalMoveException();
        }

//        if (!end.isEmpty()){
//            if (end.getPiece() instanceof King){
//                Game.getInstance().setGameOver();
//            }
//        }

        end.setPiece(start.getPiece());
        start.setPiece(null);

        if (end.getPiece() instanceof IHasMoved){
            ((IHasMoved) end.getPiece()).setPieceMoved();
        }

        Game.getInstance().flipState();

    }

    public static boolean canMove(Square start, Square end){

        if (start.isEmpty()){
            return false;
        }

        Piece piece = start.getPiece();
        if (!piece.isMoveValid(start.getBoardPosition(), end.getBoardPosition())){
            return false;
        }

        if (!end.isEmpty()){
            if (end.getPiece().getColour() == piece.getColour()){
                return false;
            }
        }

        if (piece.canJumpOverPieces()){
           return true;
        }

        Distance2D distance = start.getBoardPosition().distanceTo(end.getBoardPosition());

        if (Math.abs(distance.getxDistance()) == Math.abs(distance.getyDistance())){
            int newX, newY, xStep, yStep;

            if (distance.getxDistance() > 0){
                xStep = 1;
            }
            else{
                xStep = -1;
            }

            if (distance.getyDistance() > 0){
                yStep = 1;
            }
            else{
                yStep = -1;
            }


            newX = start.getBoardPosition().getxPosition().ordinal() +  xStep;
            newY = start.getBoardPosition().getyPosition() + yStep;

            Square square;
            while (newX != end.getBoardPosition().getxPosition().ordinal()){
                square = Board.getInstance().getSquare(new BoardPosition(XPosition.values()[newX], newY));

                newX += xStep;
                newY += yStep;

                if (!square.isEmpty()){
                    return false;
                }

            }

        }

        if (Math.abs(distance.getxDistance()) != 0 && distance.getyDistance() == 0){
            int newX, xStep;

            if (distance.getxDistance() > 0){
                xStep = 1;
            }
            else{
                xStep = -1;
            }

            newX = start.getBoardPosition().getxPosition().ordinal() +  xStep;

            Square square;
            while (newX != end.getBoardPosition().getxPosition().ordinal()){
                square = Board.getInstance().getSquare(new BoardPosition(XPosition.values()[newX], start.getBoardPosition().getyPosition()));

                newX += xStep;
                if (!square.isEmpty()){
                    return false;
                }
            }
        }

        if (Math.abs(distance.getyDistance()) != 0 && distance.getxDistance() == 0){
            int newY, yStep;

            if (distance.getyDistance() > 0){
                yStep = 1;
            }
            else{
                yStep = -1;
            }

            newY = start.getBoardPosition().getyPosition() + yStep;

            Square square;
            while (newY != end.getBoardPosition().getyPosition()){
                square = Board.getInstance().getSquare(new BoardPosition(start.getBoardPosition().getxPosition(), newY));

                newY += yStep;
                if (!square.isEmpty()){
                    return false;
                }
            }
        }

        if (piece instanceof Pawn && distance.getxDistance()!= 0){
            if (end.isEmpty()){
                return false;
            }
        }

        if (piece instanceof Pawn && distance.getxDistance() == 0 && !end.isEmpty()){
            return false;
        }

        return true;
    }

}
