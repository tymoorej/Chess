package Tests;

import Board.BoardPosition;
import Enums.XPosition;
import Pieces.Bishop;
import Pieces.Colour;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BishopTest {

    @Test
    void isMoveValid() {
        Bishop bishop = new Bishop(Colour.WHITE);

        BoardPosition start = new BoardPosition(XPosition.D, 4);

        BoardPosition end1 = new BoardPosition(XPosition.D, 5);
        BoardPosition end2 = new BoardPosition(XPosition.D, 3);
        BoardPosition end3 = new BoardPosition(XPosition.E, 4);
        BoardPosition end4 = new BoardPosition(XPosition.C, 4);

        BoardPosition end5 = new BoardPosition(XPosition.E, 3);
        BoardPosition end6 = new BoardPosition(XPosition.C, 3);
        BoardPosition end7 = new BoardPosition(XPosition.E, 5);
        BoardPosition end8 = new BoardPosition(XPosition.C, 5);

        BoardPosition end9 = new BoardPosition(XPosition.G, 7);

        assertFalse(bishop.isMoveValid(start,end1));
        assertFalse(bishop.isMoveValid(start,end2));
        assertFalse(bishop.isMoveValid(start,end3));
        assertFalse(bishop.isMoveValid(start,end4));

        assertTrue(bishop.isMoveValid(start,end5));
        assertTrue(bishop.isMoveValid(start,end6));
        assertTrue(bishop.isMoveValid(start,end7));
        assertTrue(bishop.isMoveValid(start,end8));

        assertTrue(bishop.isMoveValid(start,end9));

    }
}