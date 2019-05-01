package Tests;

import Board.BoardPosition;
import Enums.XPosition;
import Pieces.Colour;
import Pieces.Rook;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RookTest {

    @Test
    void isMoveValid() {
        Rook rook = new Rook(Colour.WHITE);
        BoardPosition start = new BoardPosition(XPosition.E, 2);

        BoardPosition end1 = new BoardPosition(XPosition.E, 6);
        BoardPosition end2 = new BoardPosition(XPosition.D, 2);
        BoardPosition end3 = new BoardPosition(XPosition.H, 2);
        BoardPosition end4 = new BoardPosition(XPosition.E, 1);

        BoardPosition end5 = new BoardPosition(XPosition.E, 2);
        BoardPosition end6 = new BoardPosition(XPosition.F, 3);


        assertTrue(rook.isMoveValid(start, end1));
        assertTrue(rook.isMoveValid(start, end2));
        assertTrue(rook.isMoveValid(start, end3));
        assertTrue(rook.isMoveValid(start, end4));
        assertFalse(rook.isMoveValid(start, end5));
        assertFalse(rook.isMoveValid(start, end6));
    }
}