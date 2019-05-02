package Tests;

import BoardHelpers.BoardPosition;
import Enums.XPosition;
import Pieces.Colour;
import Pieces.King;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KingTest {

    @Test
    void isMoveValid() {
        King king = new King(Colour.WHITE);
        BoardPosition start = new BoardPosition(XPosition.E, 2);

        BoardPosition end1 = new BoardPosition(XPosition.D, 3);
        BoardPosition end2 = new BoardPosition(XPosition.E, 3);
        BoardPosition end3 = new BoardPosition(XPosition.F, 3);
        BoardPosition end4 = new BoardPosition(XPosition.D, 2);
        BoardPosition end5 = new BoardPosition(XPosition.E, 2);
        BoardPosition end6 = new BoardPosition(XPosition.F, 2);
        BoardPosition end7 = new BoardPosition(XPosition.D, 1);
        BoardPosition end8 = new BoardPosition(XPosition.E, 1);
        BoardPosition end9 = new BoardPosition(XPosition.F, 1);

        BoardPosition end10 = new BoardPosition(XPosition.E, 4);

        assertTrue(king.isMoveValid(start, end1));
        assertTrue(king.isMoveValid(start, end2));
        assertTrue(king.isMoveValid(start, end3));
        assertTrue(king.isMoveValid(start, end4));
        assertFalse(king.isMoveValid(start, end5));
        assertTrue(king.isMoveValid(start, end6));
        assertTrue(king.isMoveValid(start, end7));
        assertTrue(king.isMoveValid(start, end8));
        assertTrue(king.isMoveValid(start, end9));
        assertFalse(king.isMoveValid(start, end10));

        King blackKing = new King(Colour.BLACK);
        BoardPosition start2 = new BoardPosition(XPosition.E, 8);
        BoardPosition end11 = new BoardPosition(XPosition.D, 6);

        assertFalse(blackKing.isMoveValid(start2,end11));
    }
}