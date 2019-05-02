package Tests;

import BoardHelpers.BoardPosition;
import Enums.XPosition;
import Pieces.Colour;
import Pieces.Queen;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueenTest {

    @Test
    void isMoveValid() {
        Queen queen = new Queen(Colour.WHITE);

        BoardPosition start = new BoardPosition(XPosition.E, 2);

        BoardPosition end1 = new BoardPosition(XPosition.E, 6);
        BoardPosition end2 = new BoardPosition(XPosition.D, 2);
        BoardPosition end3 = new BoardPosition(XPosition.H, 2);
        BoardPosition end4 = new BoardPosition(XPosition.E, 1);

        BoardPosition end5 = new BoardPosition(XPosition.E, 2);

        assertTrue(queen.isMoveValid(start, end1));
        assertTrue(queen.isMoveValid(start, end2));
        assertTrue(queen.isMoveValid(start, end3));
        assertTrue(queen.isMoveValid(start, end4));

        assertFalse(queen.isMoveValid(start, end5));

        start = new BoardPosition(XPosition.D, 4);

        BoardPosition end6 = new BoardPosition(XPosition.E, 3);
        BoardPosition end7 = new BoardPosition(XPosition.C, 3);
        BoardPosition end8 = new BoardPosition(XPosition.E, 5);
        BoardPosition end9 = new BoardPosition(XPosition.C, 5);

        assertTrue(queen.isMoveValid(start, end6));
        assertTrue(queen.isMoveValid(start, end7));
        assertTrue(queen.isMoveValid(start, end8));
        assertTrue(queen.isMoveValid(start, end9));

        BoardPosition end10 = new BoardPosition(XPosition.E, 2);

        assertFalse(queen.isMoveValid(start, end10));
    }
}