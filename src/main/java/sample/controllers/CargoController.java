package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.Main;
import sample.models.Cargo;
import sample.utils.RestApi;

public class CargoController {
    @FXML
    private TableView<Cargo> cargoTable;

    @FXML
    private TableColumn<Cargo, String> cargoNameColumn;

    @FXML
    private TableColumn<Cargo, Integer> cargoWeightColumn;

    @FXML
    private TableColumn<Cargo, String> cargoOrderColumn;

    private Main main;
    private RestApi myApiSession = new RestApi();

    public CargoController(){}

    @FXML
    private void initialize(){
        cargoNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        cargoWeightColumn.setCellValueFactory(cellData -> cellData.getValue().weightProperty().asObject());
        cargoOrderColumn.setCellValueFactory(cellData -> cellData.getValue().getOrder().getClient_id().loginProperty());
    }
    public void addCargoData(){
        main.updateCargoTable();
        cargoTable.setItems(main.getCargoData());
    }
    @FXML
    private void handleDeleteCargo() {
        int selectedIndex = cargoTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Cargo currentCargo = cargoTable.getItems().get(selectedIndex);
            if (myApiSession.deleteCargo(currentCargo)) {
                cargoTable.getItems().remove(selectedIndex);
            }
        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("Ошибка");
            alert.setHeaderText("Не выбрано клиента для удаления");
            alert.setContentText("Выберите клиента, которого хотите удалить");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleCreateCargo() {
        Cargo newCargo = new Cargo( -1L, null, 0, null);
        boolean okClicked = main.showCargoEditDialog(newCargo);
        if (okClicked) {
            myApiSession.createCargo(newCargo);
            main.updateCargoTable();
        }
    }

    @FXML
    private void handleEditCargo() {
        Cargo selectedCargo = cargoTable.getSelectionModel().getSelectedItem();
        if (selectedCargo != null) {
            boolean okClicked = main.showCargoEditDialog(selectedCargo);
            if (okClicked) {
                myApiSession.updateCargo(selectedCargo);
                this.cargoTable.setItems(main.getCargoData());
            }

        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleCancel() {
        main.showMainpage();
    }

    public void setMain(Main main){
        this.main = main;
        cargoTable.setItems(main.getCargoData());
    }

}
