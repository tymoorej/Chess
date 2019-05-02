package BoardHelpers;

import Pieces.Piece;

public class Square implements Copyable<Square>{
    private BoardPosition boardPosition;
    private Piece piece;

    public Square(BoardPosition boardPosition) {
        this.boardPosition = boardPosition;
    }

    public BoardPosition getBoardPosition() {
        return boardPosition;
    }

    public void setBoardPosition(BoardPosition boardPosition) {
        this.boardPosition = boardPosition;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean isEmpty(){
        return piece == null;
    }

    @Override
    public Square getCopy() {
        return null;
    }
}
