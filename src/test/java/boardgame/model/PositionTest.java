package boardgame.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    Position position = new Position(0,0);



    @Test
    void moveTo() {


        assertEquals(new Position(-1,-2),position.moveTo(KnightDirection.LEFT_UP));
        assertEquals(new Position(1,-2),position.moveTo(KnightDirection.LEFT_DOWN));
        assertEquals(new Position(-1,2),position.moveTo(KnightDirection.RIGHT_UP));
        assertEquals(new Position(1,2),position.moveTo(KnightDirection.RIGHT_DOWN));
        assertEquals(new Position(2,1),position.moveTo(KnightDirection.DOWN_RIGHT));
        assertEquals(new Position(2,-1),position.moveTo(KnightDirection.DOWN_LEFT));
        assertEquals(new Position(-2,1),position.moveTo(KnightDirection.UP_RIGHT));
        assertEquals(new Position(-2,-1),position.moveTo(KnightDirection.UP_LEFT));
    }

    @Test
    void testToString() {

        assertEquals("(0,0)",position.toString());
        Position position9 = new Position(4,20);
        assertEquals("(4,20)",position9.toString());
    }
}