package boardgame.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {



    Piece piece0 = new Piece(PieceType.BLACK,new Position(0,0));
    Piece piece1 = new Piece(PieceType.YELLOW,new Position(1,1));

    @Test
    void moveTo() {

        Piece piece2 = new Piece(PieceType.BLACK,new Position(0,0));
        piece2.moveTo(KnightDirection.UP_RIGHT);
        assertEquals(new Position(-2,1),piece2.getPosition());

        Piece piece3 = new Piece(PieceType.BLACK,new Position(0,0));
        piece3.moveTo(KnightDirection.UP_LEFT);
        assertEquals(new Position(-2,-1),piece3.getPosition());

        Piece piece4 = new Piece(PieceType.BLACK,new Position(0,0));
        piece4.moveTo(KnightDirection.DOWN_RIGHT);
        assertEquals(new Position(2,1),piece4.getPosition());

        Piece piece5 = new Piece(PieceType.BLACK,new Position(0,0));
        piece5.moveTo(KnightDirection.DOWN_LEFT);
        assertEquals(new Position(2,-1),piece5.getPosition());

        Piece piece6 = new Piece(PieceType.BLACK,new Position(0,0));
        piece6.moveTo(KnightDirection.LEFT_DOWN);
        assertEquals(new Position(1,-2),piece6.getPosition());

        Piece piece7 = new Piece(PieceType.BLACK,new Position(0,0));
        piece7.moveTo(KnightDirection.LEFT_UP);
        assertEquals(new Position(-1,-2),piece7.getPosition());

        Piece piece8 = new Piece(PieceType.BLACK,new Position(0,0));
        piece8.moveTo(KnightDirection.RIGHT_DOWN);
        assertEquals(new Position(1,2),piece8.getPosition());

        Piece piece9 = new Piece(PieceType.BLACK,new Position(0,0));
        piece9.moveTo(KnightDirection.RIGHT_UP);
        assertEquals(new Position(-1,2),piece9.getPosition());
    }



    @Test
    void testToString() {
        assertEquals("BLACK(0,0)",piece0.toString());
        assertEquals("YELLOW(1,1)",piece1.toString());
    }
}