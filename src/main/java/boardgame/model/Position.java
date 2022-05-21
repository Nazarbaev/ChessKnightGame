package boardgame.model;

public record Position(int row, int col) {

    public Position(int row, int col){
        this.col=col;
        this.row=row;
    }

    public Position moveTo(Direction direction) {
        return new Position(row + direction.getRowChange(), col + direction.getColChange());
    }


    public String toString() {
        return String.format("(%d,%d)", row, col);
    }

}