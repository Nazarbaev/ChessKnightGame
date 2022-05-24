package boardgame;

import boardgame.ResultModel.Data;
import boardgame.model.BoardGameModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

public class ResultController {

    @FXML
    private TableColumn<Data, String> player;

    @FXML
    private TableView<Data> toptenTable;

    @FXML
    private TableColumn<Data, Integer> winCount;

    @FXML
    void handleExit(ActionEvent event) {
        Platform.exit();

    }

    List<Data> result = new ArrayList<>();
    @FXML
    public void initialize() {

        toptenTable.setItems(null);
        player.setCellValueFactory(new PropertyValueFactory<>("name"));
        winCount.setCellValueFactory(new PropertyValueFactory<>("winCount"));


        ObservableList<Data> observableResult = FXCollections.observableArrayList();
        observableResult.addAll(BoardGameModel.getRepositiry().getTop(5));

        toptenTable.setItems(observableResult);
    }
}
