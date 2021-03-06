package boardgame;

import java.io.File;
import java.io.IOException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


import boardgame.ResultModel.Result;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import javafx.stage.Stage;
import org.tinylog.Logger;


import boardgame.model.BoardGameModel;
import boardgame.model.KnightDirection;
import boardgame.model.Position;

public class BoardGameController {

    private enum SelectionPhase {
        SELECT_FROM,
        SELECT_TO;

        public SelectionPhase alter() {
            return switch (this) {
                case SELECT_FROM -> SELECT_TO;
                case SELECT_TO -> SELECT_FROM;
            };
        }
    }

    private SelectionPhase selectionPhase = SelectionPhase.SELECT_FROM;

    private List<Position> selectablePositions = new ArrayList<>();



    private HashSet<Position> visitedPositions = new HashSet<>();

    private int orderOfMove = 1;



    private Position selected;


    private BoardGameModel model = new BoardGameModel();

    @FXML
    private TextField WhiteMovesField;

    @FXML
    private TextField BlackMovesField;

    @FXML
    private TextField PlayerTurn;


    @FXML
    private GridPane board;


    @FXML
    private void initialize() {
        try {
            BoardGameModel.getRepositiry() .loadFromFile(new File("reuslts.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        createBoard();
        createPieces();
        setSelectablePositions();
        showSelectablePositions();
        BlackMovesField.textProperty().bind(model.numberOfMovesBlackProperty().asString());
        WhiteMovesField.textProperty().bind(model.numberOfMovesWhiteProperty().asString());
        PlayerTurn.textProperty().bind(model.nameofWhitePlayerProperty().concat(" turn"));
        model.setTime((LocalDateTime.now()));

    }

    @FXML
    private void switchScene(ActionEvent event) throws IOException {
        if (selectablePositions.isEmpty()) {



            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/Result.fxml"));
            stage.setTitle("Result of the game");
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            Logger.info("Game is not over");
        }

    }


    private void createBoard() {
        for (int i = 0; i < board.getRowCount(); i++) {
            for (int j = 0; j < board.getColumnCount(); j++) {
                var square = createSquare();
                board.add(square, j, i);
            }
        }
    }

    private StackPane createSquare() {
        var square = new StackPane();
        square.getStyleClass().add("square");
        square.setOnMouseClicked(this::handleMouseClick);
        return square;
    }

    private void createPieces() {
        for (int i = 0; i < model.getPieceCount(); i++) {
            model.positionProperty(i).addListener(this::piecePositionChange);
            var piece = createPiece(Color.valueOf(model.getPieceType(i).name()));
            getSquare(model.getPiecePosition(i)).getChildren().add(piece);
        }
    }


    private Circle createPiece(Color color) {
        var piece = new Circle(30);
        piece.setFill(color);
        return piece;
    }

    @FXML
    private void handleMouseClick(MouseEvent event) {
        var square = (StackPane) event.getSource();
        var row = GridPane.getRowIndex(square);
        var col = GridPane.getColumnIndex(square);
        var position = new Position(row, col);
        Logger.debug("Click on square {}", position);
        if (orderOfMove == 1) {
            handleClickOnSquare(position);

        }
        if (orderOfMove == 0) {
            handleClickOnSquare0(position);

        }

    }

    private void handleClickOnSquare(Position position) {


        switch (selectionPhase) {
            case SELECT_FROM -> {
                if (selectablePositions.get(1).equals(position)) {
                    selectPosition(position);
                    alterSelectionPhase();
                    orderOfMove = 0;

                }
            }
            case SELECT_TO -> {
                if (selectablePositions.contains(position)) {
                    var pieceNumber = model.getPieceNumber(selected).getAsInt();
                    var direction = KnightDirection.of(position.row() - selected.row(), position.col() - selected.col());
                    Logger.debug("Moving piece {} {}", pieceNumber, direction);
                    model.move(pieceNumber, direction); //pieceNumber is
                    deselectSelectedPosition();
                    alterSelectionPhase();
                    model.increaseMoves("black");
                    PlayerTurn.textProperty().bind(model.nameofWhitePlayerProperty().concat(" turn"));

                }
            }
        }
    }


    private void handleClickOnSquare0(Position position) {

        switch (selectionPhase) {
            case SELECT_FROM -> {
                if (selectablePositions.get(0).equals(position)) {
                    selectPosition(position);
                    alterSelectionPhase();
                    orderOfMove = 1;

                }
            }
            case SELECT_TO -> {
                if (selectablePositions.contains(position)) {
                    var pieceNumber = model.getPieceNumber(selected).getAsInt();
                    var direction = KnightDirection.of(position.row() - selected.row(), position.col() - selected.col());
                    Logger.debug("Moving piece  {} {}", pieceNumber, direction);
                    model.move(pieceNumber, direction); //pieceNumber is
                    deselectSelectedPosition();
                    alterSelectionPhase();
                    model.increaseMoves("white");
                    PlayerTurn.textProperty().bind(model.nameofBlackPlayerProperty().concat(" turn"));

                }
            }
        }
    }


    private void alterSelectionPhase() {
        selectionPhase = selectionPhase.alter();
        hideSelectablePositions();
        setSelectablePositions();
        showSelectablePositions();
    }

    private void selectPosition(Position position) {
        selected = position;
        showSelectedPosition();
    }

    private void showSelectedPosition() {
        var square = getSquare(selected);
        square.getStyleClass().add("selected");
    }

    private void deselectSelectedPosition() {
        hideSelectedPosition();
        selected = null;
    }

    private void hideSelectedPosition() {
        var square = getSquare(selected);
        square.getStyleClass().remove("selected");
    }

    private void setSelectablePositions() {


        selectablePositions.clear();
        visitedPositions.addAll(model.getPiecePositions());

        switch (selectionPhase) {
            case SELECT_FROM -> {
                selectablePositions.addAll(model.getPiecePositions());


            }
            case SELECT_TO -> {
                var pieceNumber = model.getPieceNumber(selected).getAsInt();
                for (var direction : model.getValidMoves(pieceNumber)) {
                    selectablePositions.add(selected.moveTo(direction));
                }
                selectablePositions.removeAll(visitedPositions);
                if (selectablePositions.isEmpty()) {
                     gameOver();
                }


            }
        }
    }

    private void gameOver(){
        Logger.info("game is over");

        if (model.getNumberOfMovesBlack() < model.getNumberOfMovesWhite()) {

            model.setWinnerName(model.getNameOfWhitePlayer());

            var alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Game is over");
            alert.setContentText(model.getNameOfWhitePlayer() + " wins with " + model.getNumberOfMovesWhite() + " moves");
            alert.showAndWait();
        } else {
            model.setWinnerName(model.getNameOfBlackPlayer());
            var alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Game is over");
            alert.setContentText(model.getNameOfBlackPlayer() + " wins with " + model.getNumberOfMovesBlack() + " moves");
            alert.showAndWait();
        }
        BoardGameModel.getRepositiry() .add(
                Result.builder()
                        .blackPlayer(model.getNameOfBlackPlayer())
                        .whitePlayer(model.getNameOfWhitePlayer())
                        .numberOfMovesBlack(model.getNumberOfMovesBlack())
                        .numberOfMovesWhite(model.getNumberOfMovesWhite())
                        .winner(model.getWinnerName())
                        .time(model.getTime())
                        .build()
        );
        try {
            BoardGameModel.getRepositiry() .saveToFile(new File("reuslts.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void exit(int Moves, String player) {
        var alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(player + " wins with" + Moves + " moves");
        alert.setContentText("Game is Over");
        alert.showAndWait();

    }

    private void showSelectablePositions() {
        for (var selectablePosition : selectablePositions) {
            var square = getSquare(selectablePosition);
            square.getStyleClass().add("selectable");
        }
    }

    private void hideSelectablePositions() {
        for (var selectablePosition : selectablePositions) {
            var square = getSquare(selectablePosition);
            square.getStyleClass().remove("selectable");
        }
    }

    private StackPane getSquare(Position position) {
        for (var child : board.getChildren()) {
            if (GridPane.getRowIndex(child) == position.row() && GridPane.getColumnIndex(child) == position.col()) {
                return (StackPane) child;
            }
        }
        throw new AssertionError();
    }

    private void piecePositionChange(ObservableValue<? extends Position> observable, Position oldPosition, Position newPosition) {
        Logger.debug("Move: {} -> {}", oldPosition, newPosition);
        StackPane oldSquare = getSquare(oldPosition);
        StackPane newSquare = getSquare(newPosition);
        newSquare.getChildren().addAll(oldSquare.getChildren());
        oldSquare.getChildren().clear();
    }

}
