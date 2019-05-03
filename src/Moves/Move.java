package Moves;

import BoardHelpers.Board;
import BoardHelpers.BoardPosition;
import BoardHelpers.Distance2D;
import BoardHelpers.Square;
import Enums.XPosition;
import Exceptions.IllegalMoveException;
import GameHandlers.Game;
import Pieces.*;

import java.util.ArrayList;
import java.util.Objects;

public class Move {
    private Square start;
    private Square end;
    private boolean isCasle;
    private Rook castledRook;
    private BoardPosition oldRookPos;
    private BoardPosition castledRookPos;

    public Move(Square start, Square end) {
        this.start = start;
        this.end = end;
        isCasle = false;
    }

    public Square getStart() {
        return start;
    }

    public void setStart(Square start) {
        this.start = start;
    }

    public Square getEnd() {
        return end;
    }

    public void setEnd(Square end) {
        this.end = end;
    }

    public void doMove(boolean checkForCheck){
        if (!canMove(checkForCheck, Board.getInstance())){
            throw new IllegalMoveException();
        }

        if (!end.isEmpty()){
            Game.getInstance().setLastCaptureRound(Game.getInstance().getRound());
        }

        movePiece(end);

        if (end.getPiece() instanceof IHasMoved){
            ((IHasMoved) end.getPiece()).setPieceMoved();
        }

        if (isCasle){
            Board.getInstance().getSquare(castledRookPos).setPiece(castledRook);
            Board.getInstance().getSquare(oldRookPos).setPiece(null);
            isCasle = false;
            castledRook = null;
            castledRookPos = null;
            oldRookPos = null;
        }


        if (end.getPiece() instanceof Pawn && (end.getBoardPosition().getyPosition() == 8 ||
                end.getBoardPosition().getyPosition() == 1)){
            end.setPiece(new Queen(end.getPiece().getColour()));
        }

        Game.getInstance().flipState();
    }

    private void movePiece(Square endSquare){
        endSquare.setPiece(start.getPiece());
        start.setPiece(null);
    }

    public boolean canMove(boolean checkForCheck, Board board){
        Piece piece = start.getPiece();
        Distance2D distance = start.getBoardPosition().distanceTo(end.getBoardPosition());

        if (start.isEmpty()){
            return false;
        }

        if (!isCastleValid(piece, distance, board, checkForCheck)){
            if (!isEndValid(piece)){
                return false;
            }

            if (!piece.canJumpOverPieces()){
                if (!isDiagonalMoveValid(distance, board)){
                    return false;
                }

                if (!isStraightMoveValid(distance, board)){
                    return false;
                }

                if (!isPawnMoveValid(piece, distance)){
                    return false;
                }
            }
        }

        if (checkForCheck && moveWouldPutPlayerInCheck(piece.getColour(), end)){
            return false;
        }

        return true;
    }

    private boolean isCastleValid(Piece piece, Distance2D distance, Board board, boolean checkForCheck) {

        if (!checkForCheck){
            return false;
        }

        if (!(piece instanceof King)){
            return false;
        }

        King king = (King) piece;

        if (king.hasPieceMoved()){
            return false;
        }

        if (distance.getyDistance() != 0){
            return false;
        }

        if (!end.isEmpty()){
            return false;
        }


        BoardPosition rookPos;
        int step;
        if (distance.getxDistance() == 2){
            rookPos = new BoardPosition(XPosition.values()[end.getBoardPosition().getxPosition().ordinal() + 1],
                    end.getBoardPosition().getyPosition());
            step = 1;
        }
        else if (distance.getxDistance() == -2){
            rookPos = new BoardPosition(XPosition.values()[end.getBoardPosition().getxPosition().ordinal() - 2],
                    end.getBoardPosition().getyPosition());
            step = -1;
        }
        else{
            return false;
        }

        if (board.getSquare(rookPos).isEmpty()){
            return false;
        }

        Piece possibleRook = board.getSquare(rookPos).getPiece();

        if (!(possibleRook instanceof Rook)){
            return false;
        }

        Rook rook = (Rook) possibleRook;

        if (rook.hasPieceMoved()){
            return false;
        }

        int x = start.getBoardPosition().getxPosition().ordinal() + step;
        int xEnd = end.getBoardPosition().getxPosition().ordinal() + step;
        Square square;
        while (x != xEnd){
            square = board.getSquare(new BoardPosition(XPosition.values()[x],start.getBoardPosition().getyPosition()));
            if (!square.isEmpty()){
                return false;
            }


            if (moveWouldPutPlayerInCheck(king.getColour(), square)){
                return false;
            }

            x += step;
        }

        if (moveWouldPutPlayerInCheck(king.getColour(), end)){
            return false;
        }

        if (moveWouldPutPlayerInCheck(king.getColour(), board.getSquare(rookPos))){
            return false;
        }

        if (board.isColourInCheck(piece.getColour())){
            return false;
        }

        isCasle = true;
        castledRook = rook;
        castledRookPos = new BoardPosition(XPosition.values()[xEnd - 2*step], rookPos.getyPosition());
        oldRookPos = rookPos;
        return true;
    }

    private boolean moveWouldPutPlayerInCheck(Colour colour, Square endSquare) {
        Board newBoard = Board.getInstance().getCopy();
        Square newStart = newBoard.getSquare(start.getBoardPosition());
        Square newEnd = newBoard.getSquare(endSquare.getBoardPosition());
        Move newMove = new Move(newStart, newEnd);
        newMove.movePiece(newEnd);
        return newBoard.isColourInCheck(colour);
    }

    private boolean isPawnMoveValid(Piece piece, Distance2D distance) {
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

    private boolean isStraightMoveValid(Distance2D distance, Board board) {
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
                square = board.getSquare(new BoardPosition(XPosition.values()[newX], start.getBoardPosition().getyPosition()));

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
                square = board.getSquare(new BoardPosition(start.getBoardPosition().getxPosition(), newY));

                newY += yStep;
                if (!square.isEmpty()){
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isDiagonalMoveValid(Distance2D distance, Board board) {
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
                square = board.getSquare(new BoardPosition(XPosition.values()[newX], newY));

                newX += xStep;
                newY += yStep;

                if (!square.isEmpty()){
                    return false;
                }

            }

        }

        return true;
    }

    public boolean isEndValid(Piece piece){
        if (!piece.isMoveValid(start.getBoardPosition(), end.getBoardPosition())){
            return false;
        }
        if (!end.isEmpty()){
            if (end.getPiece().getColour() == piece.getColour()){
                return false;
            }
        }
        return true;
    }

    public static ArrayList<Move> getAvailableMoves(Colour colour, boolean checkForCheck, Board board){
        ArrayList<Move> moves = new ArrayList<>();

        Square start, end;
        Move move;
        for (int i = 0; i < Board.xSize; i++){
            for (int j = 0; j < Board.ySize; j++){
                start = board.getSquare(new BoardPosition(XPosition.values()[i], j+1));
                for (int k = 0; k < Board.xSize; k++){
                    for (int l = 0; l < Board.ySize; l++){
                        end = board.getSquare(new BoardPosition(XPosition.values()[k], l+1));
                        move = new Move(start, end);


                        if (start.isEmpty()){
                            continue;
                        }

                        if (!start.getPiece().getColour().equals(colour)){
                            continue;
                        }

                        if (move.canMove(checkForCheck, board)){
                            moves.add(new Move(start, end));
                        }
                    }
                }
            }
        }

        return  moves;
    }

    @Override
    public String toString() {
        return start.getBoardPosition().toString() + "->" + end.getBoardPosition().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Move)) return false;
        Move move = (Move) o;
        return Objects.equals(start, move.start) &&
                Objects.equals(end, move.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
