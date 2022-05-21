package boardgame.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleObjectProperty;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BoardGameModelTest {

    BoardGameModel model = new BoardGameModel(new Piece(PieceType.BLACK, new Position(3, 3)),new Piece(PieceType.YELLOW, new Position(5, 5)));

    @Test
    void getPieceCount() {
        assertEquals(2,model.getPieceCount());
    }

    @Test
    void increaseMoves() {

        model.increaseMoves("white");
        assertEquals(1,model.getNumberOfMovesWhite());
        model.increaseMoves("black");
        assertEquals(1,model.getNumberOfMovesWhite());
    }

    @Test
    void getPieceType() {
        assertEquals(PieceType.BLACK,model.getPieceType(0));
        assertEquals(PieceType.YELLOW,model.getPieceType(1));
    }

    @Test
    void getPiecePosition() {
        assertEquals(new Position(3,3),model.getPiecePosition(0));
        assertEquals(new Position(5,5),model.getPiecePosition(1));
    }



    @Test
    void isValidMove() {
        assertTrue(model.isValidMove(0,KnightDirection.DOWN_RIGHT));
        assertTrue(model.isValidMove(0,KnightDirection.RIGHT_DOWN));
        assertTrue(model.isValidMove(0,KnightDirection.RIGHT_UP));
        assertTrue(model.isValidMove(0,KnightDirection.DOWN_LEFT));
        assertTrue(model.isValidMove(0,KnightDirection.UP_LEFT));
        assertTrue(model.isValidMove(0,KnightDirection.UP_RIGHT));
        assertTrue(model.isValidMove(0,KnightDirection.LEFT_UP));
        assertTrue(model.isValidMove(0,KnightDirection.LEFT_DOWN));

    }

    @Test
    void getValidMoves() {
        Set<KnightDirection> validMoves = new HashSet<>();
        validMoves.add(KnightDirection.DOWN_RIGHT);
        validMoves.add(KnightDirection.RIGHT_DOWN);
        validMoves.add(KnightDirection.RIGHT_UP);
        validMoves.add(KnightDirection.DOWN_LEFT);
        validMoves.add(KnightDirection.UP_LEFT);
        validMoves.add(KnightDirection.UP_RIGHT);
        validMoves.add(KnightDirection.LEFT_UP);
        validMoves.add(KnightDirection.LEFT_DOWN);
        assertEquals(validMoves,model.getValidMoves(0));
        assertEquals(validMoves,model.getValidMoves(1));
    }

    @Test
    void move() {

        model.move(0,KnightDirection.LEFT_UP);
        assertEquals(new Position(2,1),model.getPiecePosition(0));

        model.move(0,KnightDirection.RIGHT_UP);
        assertEquals(new Position( 1,3),model.getPiecePosition(0));

        model.move(0,KnightDirection.LEFT_DOWN);
        assertEquals(new Position( 2,1),model.getPiecePosition(0));

        model.move(0,KnightDirection.RIGHT_DOWN);
        assertEquals(new Position( 3,3),model.getPiecePosition(0));

        model.move(0,KnightDirection.UP_LEFT);
        assertEquals(new Position( 1,2),model.getPiecePosition(0));

        model.move(0,KnightDirection.UP_RIGHT);
        assertEquals(new Position( -1,3),model.getPiecePosition(0));

        model.move(0,KnightDirection.DOWN_LEFT);
        assertEquals(new Position( 1,2),model.getPiecePosition(0));

        model.move(0,KnightDirection.DOWN_RIGHT);
        assertEquals(new Position( 3,3),model.getPiecePosition(0));

    }

    @Test
    void isOnBoard() {
        assertTrue(BoardGameModel.isOnBoard(new Position(3,4)));
        assertFalse(BoardGameModel.isOnBoard(new Position(6,9)));
    }

    @Test
    void getPiecePositions() {
        List<Position> positions = new ArrayList<>();
        positions.add(new Position(3,3));
        positions.add(new Position(5,5));
        assertEquals(positions,model.getPiecePositions());

    }

    @Test
    void getPieceNumber() {
        assertEquals(OptionalInt.of(0),model.getPieceNumber(new Position(3,3)));
        assertEquals(OptionalInt.of(1),model.getPieceNumber(new Position(5,5)));
    }

    @Test
    void testToString() {
        assertEquals("[BLACK(3,3),YELLOW(5,5)]",model.toString());
    }
}