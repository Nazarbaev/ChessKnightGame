package boardgame.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KnightDirectionTest {



    @Test
    void of() throws IllegalArgumentException {
        assertEquals(KnightDirection.of(1,2),KnightDirection.RIGHT_DOWN);
        assertEquals(KnightDirection.of(-1,2),KnightDirection.RIGHT_UP);
        assertEquals(KnightDirection.of(-1,-2),KnightDirection.LEFT_UP);
        assertEquals(KnightDirection.of(1,-2),KnightDirection.LEFT_DOWN);
        assertEquals(KnightDirection.of(-2,-1),KnightDirection.UP_LEFT);
        assertEquals(KnightDirection.of(-2,1),KnightDirection.UP_RIGHT);
        assertEquals(KnightDirection.of(2,1),KnightDirection.DOWN_RIGHT);
        assertEquals(KnightDirection.of(2,-1),KnightDirection.DOWN_LEFT);
        assertThrows(IllegalArgumentException.class,()->{
           KnightDirection.of(3,4);
        });

    }
}