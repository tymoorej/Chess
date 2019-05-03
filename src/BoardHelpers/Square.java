package BoardHelpers;

import Pieces.Piece;

import java.util.Objects;

public class Square implements Copyable<Square>{
    private BoardPosition boardPosition;
    private Piece piece;

    public Square(BoardPosition boardPosition) {
        this.boardPosition = boardPosition;
    }

    public Square(BoardPosition boardPosition, Piece piece) {
        this.boardPosition = boardPosition;
        this.piece = piece;
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
        if (isEmpty()){
            return new Square(boardPosition.getCopy(), null);
        }

        return new Square(boardPosition.getCopy(), piece.getCopy());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Square)) return false;
        Square square = (Square) o;
        return Objects.equals(boardPosition, square.boardPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(boardPosition);
    }
}
