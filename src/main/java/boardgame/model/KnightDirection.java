package boardgame.model;
/**Knight direction enum provides direction for knight piece
 * */
public enum KnightDirection implements Direction {
    /** Left up direction of knight */
    LEFT_UP(-1, -2),
    /** Up left direction of knight */
    UP_LEFT(-2, -1),
    /** Up right direction of knight */
    UP_RIGHT(-2, 1),
    /** Right up direction of knight */
    RIGHT_UP(-1, 2),
    /** Right down direction of knight */
    RIGHT_DOWN(1, 2),
    /** Down right direction of knight */
    DOWN_RIGHT(2, 1),
    /** Down left direction of knight */
    DOWN_LEFT(2, -1),
    /** Left down direction of knight */
    LEFT_DOWN(1, -2);

    /**Row of knight that will be changed in given direction  */
    private final int rowChange;
    /**Column of knight that will be changed in given direction  */
    private final int colChange;
    /** Constructor */
    KnightDirection(int rowChange, int colChange) {
        this.rowChange = rowChange;
        this.colChange = colChange;
    }
    /** Provides  row changed in position
     * @return row changed
     * */
    public int getRowChange() {
        return rowChange;
    }
    /** Provides  column changed in position
     * @return column changed
     * */
    public int getColChange() {
        return colChange;
    }

    /** Provides direction of knight {@link KnightDirection  object}
     * @param rowChange row of piece that will be changed
     * @param colChange column  of piece that will be changed
     * @return direction of knight
     * @throws IllegalArgumentException exception
     * */
    public static KnightDirection of(int rowChange, int colChange) {
        for (var direction : values()) {
            if (direction.rowChange == rowChange && direction.colChange == colChange) {
                return direction;
            }
        }
        throw new IllegalArgumentException();
    }

    public static void main(String[] args) {

    }

}
