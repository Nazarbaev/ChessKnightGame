package boardgame.model;

import javafx.beans.property.*;

import java.util.*;

import org.tinylog.Logger;

public class BoardGameModel {


    private ReadOnlyIntegerWrapper numberOfMovesWhite= new ReadOnlyIntegerWrapper(0);

    private ReadOnlyIntegerWrapper numberOfMovesBlack= new ReadOnlyIntegerWrapper(0);

    private static ReadOnlyStringWrapper WhitePlayerName = new ReadOnlyStringWrapper("");

    private static ReadOnlyStringWrapper BlackPlayerName = new ReadOnlyStringWrapper("");


    public static int BOARD_SIZE = 8;

    private final Piece[] pieces;



    public BoardGameModel() {
        this(new Piece(PieceType.BLACK, new Position(0, 0)),

                new Piece(PieceType.YELLOW, new Position(BOARD_SIZE - 1, BOARD_SIZE - 1)));
    }

    public BoardGameModel(Piece... pieces) {
        checkPieces(pieces);
        this.pieces = pieces.clone();
    }

    private void checkPieces(Piece[] pieces) {
        var seen = new HashSet<Position>();
        for (var piece : pieces) {
            if (!isOnBoard(piece.getPosition()) || seen.contains(piece.getPosition())) {
                throw new IllegalArgumentException();
            }
            seen.add(piece.getPosition());
        }
    }

    public int getPieceCount() {
        return pieces.length;
    }

    public void increaseMoves( String name){
        var whiteMoves =1;
        var blackMoves =1;
        if(name.equals("white")){
            numberOfMovesWhite.set(numberOfMovesWhite.get()+whiteMoves);

        }
        else{
            numberOfMovesBlack.set(numberOfMovesBlack.get()+blackMoves);

        }



    }
  public int getNumberOfMovesWhite(){
        return numberOfMovesWhite.get();
  }

   public int getNumberOfMovesBlack(){
        return numberOfMovesBlack.get();
    }

    public String getNameOfWhitePlayer(){
        return WhitePlayerName.get();
    }
    public String getNameOfBlackPlayer(){
        return BlackPlayerName.get();
    }
    public void setWhitePlayerName(String name){
        WhitePlayerName.set(name);
    }
    public void setBlackPlayerName(String name){
        BlackPlayerName.set(name);
    }

    public ReadOnlyStringProperty nameofWhitePlayerProperty(){
        return WhitePlayerName.getReadOnlyProperty();
    }
    public ReadOnlyStringProperty nameofBlackPlayerProperty(){
        return BlackPlayerName.getReadOnlyProperty();
    }
  public ReadOnlyIntegerProperty numberOfMovesWhiteProperty(){
        return numberOfMovesWhite.getReadOnlyProperty();
    }

  public ReadOnlyIntegerProperty numberOfMovesBlackProperty(){
        return numberOfMovesBlack.getReadOnlyProperty();
    }

    public PieceType getPieceType(int pieceNumber) {
        return pieces[pieceNumber].getType();
    }

    public Position getPiecePosition(int pieceNumber) {
        return pieces[pieceNumber].getPosition();
    }

    public ObjectProperty<Position> positionProperty(int pieceNumber) {
        return pieces[pieceNumber].positionProperty();
    }

    public boolean isValidMove(int pieceNumber, KnightDirection direction) {
        if (pieceNumber < 0 || pieceNumber >= pieces.length) {
            throw new IllegalArgumentException();
        }
        Position newPosition = pieces[pieceNumber].getPosition().moveTo(direction);
        if (!isOnBoard(newPosition)) {
            return false;
        }
        for (var piece : pieces) {
            if (piece.getPosition().equals(newPosition)) {
                return false;
            }
        }
        return true;
    }

    public Set<KnightDirection> getValidMoves(int pieceNumber) {
        EnumSet<KnightDirection> validMoves = EnumSet.noneOf(KnightDirection.class);
        for (var direction : KnightDirection.values()) {
            if (isValidMove(pieceNumber, direction)) {
                validMoves.add(direction);
            }
        }
        return validMoves;
    }

    public void move(int pieceNumber, KnightDirection direction) {
        pieces[pieceNumber].moveTo(direction);
    }

    public static boolean isOnBoard(Position position) {
        return 0 <= position.row() && position.row() < BOARD_SIZE
                && 0 <= position.col() && position.col() < BOARD_SIZE;
    }

    public List<Position> getPiecePositions() {
        List<Position> positions = new ArrayList<>(pieces.length);
        for (var piece : pieces) {
            positions.add(piece.getPosition());
        }
        return positions;
    }

    public OptionalInt getPieceNumber(Position position) {
        for (int i = 0; i < pieces.length; i++) {
            if (pieces[i].getPosition().equals(position)) {
                return OptionalInt.of(i);
            }
        }
        return OptionalInt.empty();
    }

    public String toString() {
        StringJoiner joiner = new StringJoiner(",", "[", "]");
        for (var piece : pieces) {
            joiner.add(piece.toString());
        }
        return joiner.toString();
    }

    public static void main(String[] args) {
        BoardGameModel model = new BoardGameModel();
        Logger.debug(model);
    }

}
