package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.Main;
import sample.models.Rate;
import sample.models.Transport;
import sample.utils.RestApi;

public class RateController {
    @FXML
    private TableView<Rate> rateTable;

    @FXML
    private TableColumn<Rate, String> rateArrivalColumn;

    @FXML
    private TableColumn<Rate, String> rateDepartureColumn;

    @FXML
    private TableColumn<Rate, Integer> rateCostColumn;

    private Main main;

    private RestApi myApiSession = new RestApi();

    public RateController(){}

    @FXML
    private void initialize(){
        rateArrivalColumn.setCellValueFactory(cellData -> cellData.getValue().getArrivalTown().nameProperty());
        rateDepartureColumn.setCellValueFactory(cellData -> cellData.getValue().getDepartureTown().nameProperty());
        rateCostColumn.setCellValueFactory(cellData -> cellData.getValue().costRateProperty().asObject());
    }

    public void updateRate(){
        main.updateDriverTable();
    }

    public void addRateData(){
        main.updateRateTable();
        rateTable.setItems(main.getRateData());
    }

    @FXML
    private void handleDeleteRate() {
        int selectedIndex = rateTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Rate currentRate = rateTable.getItems().get(selectedIndex);
            if (myApiSession.deleteRate(currentRate)) {
                rateTable.getItems().remove(selectedIndex);
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
    private void handleCreateRate() {
        Rate newRate = new Rate(-1L, null, null, 0);
        boolean okClicked = main.showRateEditDialog(newRate);
        if (okClicked) {
            myApiSession.createRate(newRate);
            main.updateRateTable();
            this.rateTable.setItems(main.getRateData());
        }
    }

    @FXML
    private void handleEditRate() {
        Rate selectedRate = rateTable.getSelectionModel().getSelectedItem();
        if (selectedRate != null) {
            boolean okClicked = main.showRateEditDialog(selectedRate);
            if (okClicked) {
                myApiSession.updateRate(selectedRate);
                main.updateTransportTable();
                this.rateTable.setItems(main.getRateData());
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
        rateTable.setItems(main.getRateData());
    }
}
