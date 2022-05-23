package boardgame.model;
/** Position record to get a position of Piece
 * with two methods moveTo and toString
 * */
public record Position(int row, int col) {


    /** Moves {@link Piece } object in given direction
     * @param  direction direction in which  piece should be moved
      * @return {@code position } new position of piece
     * */
    public Position moveTo(Direction direction) {
        return new Position(row + direction.getRowChange(), col + direction.getColChange());
    }

   /** Changes Integer format to String
    * @return  row and col in String format
    * */
    public String toString() {
        return String.format("(%d,%d)", row, col);
    }

}