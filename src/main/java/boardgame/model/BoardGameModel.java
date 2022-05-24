package boardgame.model;

import boardgame.ResultModel.ResultRepositiry;
import javafx.beans.property.*;


import java.time.LocalDateTime;
import java.util.*;

import lombok.Getter;
import lombok.Setter;

/**Implements main logic of game*/
public class BoardGameModel {

    /** Number of moves of white player*/
    private ReadOnlyIntegerWrapper numberOfMovesWhite= new ReadOnlyIntegerWrapper(0);
    /** Number of moves of black player*/
    private ReadOnlyIntegerWrapper numberOfMovesBlack= new ReadOnlyIntegerWrapper(0);
    /** White player name*/
    private static ReadOnlyStringWrapper WhitePlayerName = new ReadOnlyStringWrapper("");
    /** Black player name*/
    private static ReadOnlyStringWrapper BlackPlayerName = new ReadOnlyStringWrapper("");
    @Getter
    @Setter
    private static String winnerName ;

    @Setter
    @Getter
    private  LocalDateTime time;
    @Getter
    private static ResultRepositiry repositiry = new ResultRepositiry();

    /**Board size*/
    public static int BOARD_SIZE = 8;
    /**Array of pieces type of {@link Piece  }*/
    private final  Piece[] pieces;


   /**Constructor */
    public BoardGameModel() {
        this(new Piece(PieceType.BLACK, new Position(0, 0)),

                new Piece(PieceType.YELLOW, new Position(BOARD_SIZE - 1, BOARD_SIZE - 1)));
    }
    /**Constructor
     * @param pieces pieces */
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


    /** Checks is direction valid,
     * @throws IllegalArgumentException if pieceNumber is not valid
     * @param pieceNumber number of piece
     * @param direction direction of piece
     * @return {@link Boolean} false if new position outside of board and if new position is occupied, otherwise true
     *
    * */
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
    /** Provides valid moves for knight piece
     * @param pieceNumber number of piece
     * @return {@link KnightDirection} valid directions of knight piece
     * */
    public Set<KnightDirection> getValidMoves(int pieceNumber) {
        EnumSet<KnightDirection> validMoves = EnumSet.noneOf(KnightDirection.class);
        for (var direction : KnightDirection.values()) {
            if (isValidMove(pieceNumber, direction)) {
                validMoves.add(direction);
            }
        }
        return validMoves;
    }
    /**Moves piece in given direction
     * @param  pieceNumber number of piece
     * @param direction given direction of piece
     * */

    public void move(int pieceNumber, KnightDirection direction) {
        pieces[pieceNumber].moveTo(direction);
    }
    /**Checks is a piece on board
     * @param position position of piece
     * @return {@link Boolean} true if piece on board, otherwise false
     * */
    public static boolean isOnBoard(Position position) {
        return 0 <= position.row() && position.row() < BOARD_SIZE
                && 0 <= position.col() && position.col() < BOARD_SIZE;
    }
    /** Provides position of pieces
     * @return  positions of pieces*/
    public List<Position> getPiecePositions() {
        List<Position> positions = new ArrayList<>(pieces.length);
        for (var piece : pieces) {
            positions.add(piece.getPosition());
        }
        return positions;
    }
    /**Provides number of piece from {@code pieces array}
     * @param position position of piece
     * @return {@link OptionalInt object}*/
    public OptionalInt getPieceNumber(Position position) {
        for (int i = 0; i < pieces.length; i++) {
            if (pieces[i].getPosition().equals(position)) {
                return OptionalInt.of(i);
            }
        }
        return OptionalInt.empty();
    }
    /**Changes {@link Piece piece object} to string format
     * @return piece and position in string format
     * */
    public String toString() {
        StringJoiner joiner = new StringJoiner(",", "[", "]");
        for (var piece : pieces) {
            joiner.add(piece.toString());
        }
        return joiner.toString();
    }
    /**Provides the number of pieces
     * @return pieces count */
    public int getPieceCount() {
        return pieces.length;
    }
    /** Increases number of moves both black and white piece
     * @param name color of the piece
     * */
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

   /**Provides number of moves of white player
    * @return number of moves of white player */
    public int getNumberOfMovesWhite() { return numberOfMovesWhite.get(); }
    /**Provides number of moves of black player
     * @return number of moves of black player */
    public int getNumberOfMovesBlack() { return numberOfMovesBlack.get(); }
    /**Provides name of the white player
     * @return name of the white player*/
    public String getNameOfWhitePlayer() { return WhitePlayerName.get(); }
    /**Provides name of the black player
     * @return name of the black player*/
    public String getNameOfBlackPlayer() { return BlackPlayerName.get(); }
    /**Sets the name of white player
     * @param name name of white player */
    public void setWhitePlayerName(String name) { WhitePlayerName.set(name); }
    /**Sets the name of black player
     * @param name name of black player */
    public void setBlackPlayerName(String name) { BlackPlayerName.set(name); }


    /**Provides property of name of black player
     * @return  property of name
     * */
    public ReadOnlyStringProperty nameofBlackPlayerProperty(){ return BlackPlayerName.getReadOnlyProperty(); }
    /**Provides property of name of white player
     * @return  property of name
     * */
    public ReadOnlyStringProperty nameofWhitePlayerProperty(){ return WhitePlayerName.getReadOnlyProperty(); }

    /**Provides property of moves count of white player
     * @return  property of moves count
     * */
    public ReadOnlyIntegerProperty numberOfMovesWhiteProperty(){
        return numberOfMovesWhite.getReadOnlyProperty();
    }
    /**Provides property of moves count of black player
     * @return  property of moves count
     * */
    public ReadOnlyIntegerProperty numberOfMovesBlackProperty(){
        return numberOfMovesBlack.getReadOnlyProperty();
    }
   /**Provides  type of piece
    * @param pieceNumber number of piece
    * @return type of piece*/
    public PieceType getPieceType(int pieceNumber) {
        return pieces[pieceNumber].getType();
    }
    /**Provides  position of piece
     * @param pieceNumber number of piece
     * @return position of piece*/
    public Position getPiecePosition(int pieceNumber) {
        return pieces[pieceNumber].getPosition();
    }
    /**Provides property of position of piece
     * @param  pieceNumber number of piece
     * @return property of position of piece*/
    public ObjectProperty<Position> positionProperty(int pieceNumber) {
        return pieces[pieceNumber].positionProperty();
    }

    public static void main(String[] args) {

    }

}
