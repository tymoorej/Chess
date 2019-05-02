package BoardHelpers;

import Enums.XPosition;
import GameHandlers.Game;
import Moves.Move;
import Moves.PieceMover;
import Pieces.*;

import java.util.ArrayList;

public class Board implements Copyable<Board>{
    public static final int xSize = 8;
    public static final int ySize = 8;
    private Square[][] squares;

    private static Board ourInstance = new Board();

    public static Board getInstance() {
        return ourInstance;
    }

    private Board() {
        squares = new Square[xSize][ySize];

        for (int i = 0; i < xSize; i++){
            for (int j = 0; j < ySize; j++){
                squares[i][j] = new Square(new BoardPosition(XPosition.values()[i], j + 1));
            }
        }

        placePieces();
    }

    private Board(Square[][] squares){
        this.squares = squares;
    }

    private void placePieces(){
        placeKings();
        placeQueens();
        placeBishops();
        placeKnights();
        placeRooks();
        placePawns();
    }

    private void placeKings() {
        getSquare(new BoardPosition(XPosition.E, 1)).setPiece(new King(Colour.WHITE));
        getSquare(new BoardPosition(XPosition.E, 8)).setPiece(new King(Colour.BLACK));
    }

    private void placeQueens() {
        getSquare(new BoardPosition(XPosition.D, 1)).setPiece(new Queen(Colour.WHITE));
        getSquare(new BoardPosition(XPosition.D, 8)).setPiece(new Queen(Colour.BLACK));
    }

    private void placeBishops() {
        getSquare(new BoardPosition(XPosition.C, 1)).setPiece(new Bishop(Colour.WHITE));
        getSquare(new BoardPosition(XPosition.F, 1)).setPiece(new Bishop(Colour.WHITE));

        getSquare(new BoardPosition(XPosition.C, 8)).setPiece(new Bishop(Colour.BLACK));
        getSquare(new BoardPosition(XPosition.F, 8)).setPiece(new Bishop(Colour.BLACK));
    }

    private void placeKnights() {
        getSquare(new BoardPosition(XPosition.B, 1)).setPiece(new Knight(Colour.WHITE));
        getSquare(new BoardPosition(XPosition.G, 1)).setPiece(new Knight(Colour.WHITE));

        getSquare(new BoardPosition(XPosition.B, 8)).setPiece(new Knight(Colour.BLACK));
        getSquare(new BoardPosition(XPosition.G, 8)).setPiece(new Knight(Colour.BLACK));
    }

    private void placeRooks() {
        getSquare(new BoardPosition(XPosition.A, 1)).setPiece(new Rook(Colour.WHITE));
        getSquare(new BoardPosition(XPosition.H, 1)).setPiece(new Rook(Colour.WHITE));

        getSquare(new BoardPosition(XPosition.A, 8)).setPiece(new Rook(Colour.BLACK));
        getSquare(new BoardPosition(XPosition.H, 8)).setPiece(new Rook(Colour.BLACK));
    }

    private void placePawns() {
        for (int i = 0; i < xSize; i++){
            squares[i][1].setPiece(new Pawn(Colour.WHITE));
            squares[i][6].setPiece(new Pawn(Colour.BLACK));
        }
    }

    public Square[][] getSquares(){
        return squares;
    }

    public Square getSquare(BoardPosition position){
        return squares[position.getxPosition().ordinal()][position.getyPosition() - 1];
    }

    public void updateColourInCheck(){
        ArrayList<Move> whiteMoves = PieceMover.getAvailableMoves(Colour.WHITE);
        ArrayList<Move> blackMoves = PieceMover.getAvailableMoves(Colour.BLACK);

        Piece piece;
        for (Move move : whiteMoves){
            if (!move.getEnd().isEmpty()){
                piece = move.getEnd().getPiece();
                if (piece instanceof King && piece.getColour().equals(Colour.BLACK)){
                    Game.getInstance().setInCheck(Colour.BLACK);
                    return;
                }
            }
        }

        for (Move move : blackMoves){
            if (!move.getEnd().isEmpty()){
                piece = move.getEnd().getPiece();
                if (piece instanceof King && piece.getColour().equals(Colour.WHITE)){
                    Game.getInstance().setInCheck(Colour.WHITE);
                    return;
                }
            }
        }

        Game.getInstance().setInCheck(null);

    }

    @Override
    public Board getCopy() {
        return null;
    }
}
