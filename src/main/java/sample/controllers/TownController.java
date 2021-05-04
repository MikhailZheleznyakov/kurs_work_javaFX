package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.Main;
import sample.models.Town;
import sample.utils.RestApi;

public class TownController {
    @FXML
    private TableView<Town> townTable;

    @FXML
    private TableColumn<Town, String> townNameColumn;

    @FXML
    private Label townNameLabel;

    @FXML
    private Label townInfoLabel;

    private Main main;

    private Town town;

    private RestApi myApiSession = new RestApi();

    public TownController(){}

    private void showTownDetails(Town town){
        if (town != null){
            townNameLabel.setText(town.getName());
            townInfoLabel.setText(town.getInfo());
        } else {
            townNameLabel.setText("");
            townInfoLabel.setText("");
        }
    }
    @FXML
    private void initialize(){
        townNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        showTownDetails(null);
        townTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showTownDetails(newValue));
    }

    public void updateTown(){
        main.updateTownTable();
        townTable.setItems(main.getTownData());
    }

    @FXML
    private void handleDeleteTown() {
        int selectedIndex = townTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Town currentTown = townTable.getItems().get(selectedIndex);
            if (myApiSession.deleteTown(currentTown)) {
                townTable.getItems().remove(selectedIndex);
            }
            townTable.getItems().remove(selectedIndex);
        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("Ошибка");
            alert.setHeaderText("Не выбрано города для удаления");
            alert.setContentText("Выберите город, который хотите удалить");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewTown() {
        Town newTown = new Town(-1L,null, null);
        boolean okClicked = main.showTownEditDialog(newTown);
        if (okClicked) {
            myApiSession.createTown(newTown);
            main.getTownData().add(newTown);
            this.townTable.setItems(main.getTownData());
        }
    }

    @FXML
    private void handleEditTown() {
        Town selectedTown = townTable.getSelectionModel().getSelectedItem();
        if (selectedTown != null) {
            boolean okClicked = main.showTownEditDialog(selectedTown);
            if (okClicked) {
                myApiSession.updateTown(selectedTown);
                this.townTable.setItems(main.getTownData());
            }

        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("Не выбрано города");
            alert.setContentText("Выберите город");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleCancel(){main.showMainpage();}

    public void setMain(Main main) {
        this.main = main;
    }
}
