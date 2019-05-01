package Tests;

import Board.Board;
import Board.Square;
import Board.XPosition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    public void constructorTest(){
        Board board = new Board();
        Square[][] squares = board.getSquares();

        for (int i = 0; i < board.xSize; i++){
            for (int j = 0; j < board.ySize; j++){
                assertEquals(XPosition.values()[i].name(), squares[i][j].getBoardPosition().getxPosition().name());
                assertEquals(j + 1, squares[i][j].getBoardPosition().getyPosition());
                assertNull(squares[i][j].getPiece());
                assertTrue(squares[i][j].isEmpty());
            }
        }
    }
}