package Tests;

import BoardHelpers.BoardPosition;
import BoardHelpers.Distance2D;
import Enums.XPosition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardPositionTest {

    @Test
    void distanceTo() {
        BoardPosition boardPosition1 = new BoardPosition(XPosition.A, 4);
        BoardPosition boardPosition2 = new BoardPosition(XPosition.C, 3);
        Distance2D expectedDistance = new Distance2D(2, -1);

        assertEquals(expectedDistance.getxDistance(), boardPosition1.distanceTo(boardPosition2).getxDistance());
        assertEquals(expectedDistance.getyDistance(), boardPosition1.distanceTo(boardPosition2).getyDistance());

        assertEquals(expectedDistance.getxDistance(), -1 * boardPosition2.distanceTo(boardPosition1).getxDistance());
        assertEquals(expectedDistance.getyDistance(), -1 * boardPosition2.distanceTo(boardPosition1).getyDistance());
    }
}