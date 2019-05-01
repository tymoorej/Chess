package Tests;

import Board.Board;
import Board.Square;
import Enums.XPosition;
import Pieces.*;
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

                if (j == 1){
                    assertTrue(squares[i][j].getPiece() instanceof Pawn && squares[i][j].getPiece().getColour() == Colour.WHITE);
                    continue;
                }

                if (j == 6){
                    assertTrue(squares[i][j].getPiece() instanceof Pawn && squares[i][j].getPiece().getColour() == Colour.BLACK);
                    continue;
                }

                if (j == 0){
                    if (i == 0 || i == 7){
                        assertTrue(squares[i][j].getPiece() instanceof Rook && squares[i][j].getPiece().getColour() == Colour.WHITE);
                        continue;
                    }

                    if (i == 1 || i == 6){
                        assertTrue(squares[i][j].getPiece() instanceof Knight && squares[i][j].getPiece().getColour() == Colour.WHITE);
                        continue;
                    }

                    if (i == 2 || i == 5){
                        assertTrue(squares[i][j].getPiece() instanceof Bishop && squares[i][j].getPiece().getColour() == Colour.WHITE);
                        continue;
                    }

                    if (i == 3){
                        assertTrue(squares[i][j].getPiece() instanceof Queen && squares[i][j].getPiece().getColour() == Colour.WHITE);
                        continue;
                    }

                    if (i == 4){
                        assertTrue(squares[i][j].getPiece() instanceof King && squares[i][j].getPiece().getColour() == Colour.WHITE);
                        continue;
                    }
                }

                if (j == 7){
                    if (i == 0 || i == 7){
                        assertTrue(squares[i][j].getPiece() instanceof Rook && squares[i][j].getPiece().getColour() == Colour.BLACK);
                        continue;
                    }

                    if (i == 1 || i == 6){
                        assertTrue(squares[i][j].getPiece() instanceof Knight && squares[i][j].getPiece().getColour() == Colour.BLACK);
                        continue;
                    }

                    if (i == 2 || i == 5){
                        assertTrue(squares[i][j].getPiece() instanceof Bishop && squares[i][j].getPiece().getColour() == Colour.BLACK);
                        continue;
                    }

                    if (i == 3){
                        assertTrue(squares[i][j].getPiece() instanceof Queen && squares[i][j].getPiece().getColour() == Colour.BLACK);
                        continue;
                    }

                    if (i == 4){
                        assertTrue(squares[i][j].getPiece() instanceof King && squares[i][j].getPiece().getColour() == Colour.BLACK);
                        continue;
                    }
                }

                assertTrue(squares[i][j].isEmpty());

            }
        }
    }
}