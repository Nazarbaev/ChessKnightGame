package boardgame.model;

public enum KnightDirection implements Direction {

    LEFT_UP(-1, -2),
    UP_LEFT(-2, -1),
    UP_RIGHT(-2, 1),
    RIGHT_UP(-1, 2),
    RIGHT_DOWN(1, 2),
    DOWN_RIGHT(2, 1),
    DOWN_LEFT(2, -1),
    LEFT_DOWN(1, -2);

    private final int rowChange;
    private final int colChange;

    KnightDirection(int rowChange, int colChange) {
        this.rowChange = rowChange;
        this.colChange = colChange;
    }

    public int getRowChange() {
        return rowChange;
    }

    public int getColChange() {
        return colChange;
    }

    public static KnightDirection of(int rowChange, int colChange) {
        for (var direction : values()) {
            if (direction.rowChange == rowChange && direction.colChange == colChange) {
                return direction;
            }
        }
        throw new IllegalArgumentException();
    }

    public static void main(String[] args) {
        System.out.println(of(1, 2));
    }

}
