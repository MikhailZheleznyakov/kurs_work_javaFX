package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.Main;
import sample.models.Driver;
import sample.utils.RestApi;

public class DriverController {
    @FXML
    private TableView<Driver> driverTable;

    @FXML
    private TableColumn<Driver, String> driverNameColumn;

    @FXML
    private TableColumn<Driver, String> driverSurnameColumn;

    private Main main;
    private RestApi myApiSession = new RestApi();
    private Driver driver;

    public DriverController(){}


    @FXML
    private void initialize() {
        driverNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        driverSurnameColumn.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());
    }

    public void addData(){
        main.updateDriverTable();
    }



    @FXML
    private void handleDeleteDriver() {
        int selectedIndex = driverTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            driverTable.getItems().remove(selectedIndex);
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
    private void handleNewDriver() {
        Driver newDriver = new Driver(-1L, null, null);
        boolean okClicked = main.showDriverEditDialog(newDriver);
        if (okClicked) {
            myApiSession.createDriver(newDriver);
            main.updateDriverTable();
            this.driverTable.setItems(main.getDriverData());
        }
    }

    public void addDriverData(){
        main.updateDriverTable();
        driverTable.setItems(main.getDriverData());
    }

    @FXML
    private void handleEditDriver() {
        Driver selectedDriver = driverTable.getSelectionModel().getSelectedItem();
        if (selectedDriver != null) {
            boolean okClicked = main.showDriverEditDialog(selectedDriver);
            if (okClicked) {
                myApiSession.updateDriver(selectedDriver);
                this.driverTable.setItems(main.getDriverData());
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
    private void handleCancel(){
        main.showMainpage();
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
