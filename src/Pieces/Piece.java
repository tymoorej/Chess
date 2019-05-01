package Pieces;

import Board.BoardPosition;
import UI.IDrawable;

public abstract class Piece implements IDrawable {
    public abstract int getValue();
    public abstract boolean isMoveValid(BoardPosition start, BoardPosition end);
    public abstract boolean canJumpOverPieces();
    public abstract Colour getColour();
}
