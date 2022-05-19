package boardgame.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Piece {

    private final PieceType type;
    private final ObjectProperty<Position> position = new SimpleObjectProperty<>();

    public Piece(PieceType type, Position position) {
        this.type = type;
        this.position.set(position);
    }


    public PieceType getType() {
        return type;
    }

    public Position getPosition() {
        return position.get();
    }

    public void moveTo(Direction direction) {
        Position newPosition = position.get().moveTo(direction);
        position.set(newPosition);
    }

    public ObjectProperty<Position> positionProperty() {
        return position;
    }

    public String toString() {
        return type.toString() + position.get().toString();
    }

    public static void main(String[] args) {
        Piece piece = new Piece(PieceType.BLACK, new Position(0, 0));
        piece.positionProperty().addListener((observableValue, oldPosition, newPosition) -> {
            System.out.printf("%s -> %s%n ", oldPosition.toString(), newPosition.toString());
        });
        System.out.println(piece);
        piece.moveTo(KnightDirection.DOWN_RIGHT);
        System.out.println(piece);
    }
}
