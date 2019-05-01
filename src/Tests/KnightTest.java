package Tests;

import Board.BoardPosition;
import Enums.XPosition;
import Pieces.Colour;
import Pieces.Knight;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest {

    @Test
    void isMoveValid() {
        Knight knight = new Knight(Colour.WHITE);
        BoardPosition start = new BoardPosition(XPosition.E, 4);

        BoardPosition end1 = new BoardPosition(XPosition.F, 6);
        BoardPosition end2 = new BoardPosition(XPosition.D, 6);
        BoardPosition end3 = new BoardPosition(XPosition.F, 2);
        BoardPosition end4 = new BoardPosition(XPosition.D, 2);

        BoardPosition end5 = new BoardPosition(XPosition.C, 5);
        BoardPosition end6 = new BoardPosition(XPosition.C, 3);
        BoardPosition end7 = new BoardPosition(XPosition.G, 5);
        BoardPosition end8 = new BoardPosition(XPosition.G, 3);

        BoardPosition end9 = new BoardPosition(XPosition.E, 4);
        BoardPosition end10 = new BoardPosition(XPosition.D, 4);
        BoardPosition end11 = new BoardPosition(XPosition.D, 5);

        assertTrue(knight.isMoveValid(start,end1));
        assertTrue(knight.isMoveValid(start,end2));
        assertTrue(knight.isMoveValid(start,end3));
        assertTrue(knight.isMoveValid(start,end4));
        assertTrue(knight.isMoveValid(start,end5));
        assertTrue(knight.isMoveValid(start,end6));
        assertTrue(knight.isMoveValid(start,end7));
        assertTrue(knight.isMoveValid(start,end8));

        assertFalse(knight.isMoveValid(start, end9));
        assertFalse(knight.isMoveValid(start, end10));
        assertFalse(knight.isMoveValid(start, end11));

    }
}