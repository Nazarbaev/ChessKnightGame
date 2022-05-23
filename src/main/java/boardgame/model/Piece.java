package boardgame.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
/** Class Piece provides  pieces and functionality of pieces for {@link BoardGameModel class}
 * */
public class Piece {
    /**Type of pieces
     * */
    private final PieceType type;
    /**Postion of pieces {@link ObjectProperty<Position> type }*/
    private final ObjectProperty<Position> position = new SimpleObjectProperty<>();
   /** Constructor
    * @param type type of piece
    * @param position position of piece
    * */
    public Piece(PieceType type, Position position) {
        this.type = type;
        this.position.set(position);
    }

    /** Provides piece type
     * @return type {@link PieceType object}
     * */
    public PieceType getType() {
        return type;
    }
     /** Provides position of piece
      * @return position {@link Position object}
      * */
    public Position getPosition() {
        return position.get();
    }
     /** Moves piece in given direction
      * @param direction direction of knight
      * */
    public void moveTo(Direction direction) {
        Position newPosition = position.get().moveTo(direction);
        position.set(newPosition);
    }
    /**Provides position property
     * @return position of piece
     * */
    public ObjectProperty<Position> positionProperty() {
        return position;
    }

    /** Changes type and position to string format
     * @return type and position
     * */
    public String toString() {
        return type.toString() + position.get().toString();
    }

    public static void main(String[] args) {

    }
}
