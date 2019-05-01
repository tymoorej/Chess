package Pieces;

import Board.BoardPosition;

public abstract class Piece{
    public abstract int getValue();
    public abstract boolean isMoveValid(BoardPosition start, BoardPosition end);
    public abstract boolean canJumpOverPieces();
    public abstract Colour getColour();
}
