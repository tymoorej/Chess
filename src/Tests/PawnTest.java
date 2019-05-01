package Tests;

import Board.BoardPosition;
import Enums.XPosition;
import Pieces.Colour;
import Pieces.Pawn;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {

    @Test
    void isMoveValid() {
        Pawn whitePawn = new Pawn(Colour.WHITE);
        BoardPosition start = new BoardPosition(XPosition.E, 2);

        BoardPosition end1 = new BoardPosition(XPosition.E, 3);
        BoardPosition end2 = new BoardPosition(XPosition.E, 4);
        BoardPosition end3 = new BoardPosition(XPosition.E, 5);

        BoardPosition end4 = new BoardPosition(XPosition.F, 3);
        BoardPosition end5 = new BoardPosition(XPosition.D, 3);

        BoardPosition end6 = new BoardPosition(XPosition.F, 2);
        BoardPosition end7 = new BoardPosition(XPosition.D, 2);

        BoardPosition end8 = new BoardPosition(XPosition.F, 4);
        BoardPosition end9 = new BoardPosition(XPosition.D, 4);


        assertTrue(whitePawn.isMoveValid(start, end1));
        assertTrue(whitePawn.isMoveValid(start, end2));
        assertFalse(whitePawn.isMoveValid(start, end3));

        assertTrue(whitePawn.isMoveValid(start, end4));
        assertTrue(whitePawn.isMoveValid(start, end5));

        assertFalse(whitePawn.isMoveValid(start, end6));
        assertFalse(whitePawn.isMoveValid(start, end7));

        assertFalse(whitePawn.isMoveValid(start, end8));
        assertFalse(whitePawn.isMoveValid(start, end9));

        whitePawn.setPieceMoved();

        assertFalse(whitePawn.isMoveValid(start, end2));

        Pawn blackPawn = new Pawn(Colour.BLACK);
        assertFalse(blackPawn.isMoveValid(start, end1));
        assertFalse(blackPawn.isMoveValid(start, end2));

    }
}