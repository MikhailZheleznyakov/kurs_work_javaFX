package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.Main;
import sample.models.Transport;
import sample.utils.RestApi;

public class TransportController {

    @FXML
    private TableView<Transport> transportTable;

    @FXML
    private TableColumn<Transport, String> transportNameColumn;

    @FXML
    private TextField filteredTransport;

    @FXML
    private Label transportName;

    @FXML
    private Label transportCapacity;

    @FXML
    private Label transportWearout;

    @FXML
    private Label transportDriver;

    @FXML
    private Label transportType;

    private Main main;

    private RestApi myApiSession = new RestApi();


    public TransportController(){}



    private void showTransportDetails(Transport transport){
        if (transport!=null){
            transportName.setText(transport.getName());
            transportCapacity.setText(String.valueOf(transport.getCapacity()));
            transportWearout.setText(String.valueOf(transport.getWearout()));
            transportDriver.setText(transport.getDriver().getViewDriver());
            transportType.setText(transport.getTransport_type());
        } else {
            transportName.setText("");
            transportCapacity.setText(String.valueOf(0));
            transportWearout.setText(String.valueOf(0));
            transportDriver.setText("");
            transportType.setText("");
        }
    }

    @FXML
    private void initialize(){
        transportNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        showTransportDetails(null);
        transportTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showTransportDetails(newValue));
    }

    public void updateTransport(){
        main.updateTransportTable();
    }

    @FXML
    private void handleDeleteTransport() {
        int selectedIndex = transportTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Transport currentTransport = transportTable.getItems().get(selectedIndex);
            if (myApiSession.deleteTransport(currentTransport)) {
                transportTable.getItems().remove(selectedIndex);
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
    private void handleNewTransport() {
        Transport newTransport = new Transport(-1L, null, 0, 0, null, null);
        boolean okClicked = main.showTransportEditDialog(newTransport);
        if (okClicked) {
            myApiSession.createTransport(newTransport);
            main.updateTransportTable();
        }
    }

    @FXML
    private void handleEditTransport() {
        Transport selectedTransport = transportTable.getSelectionModel().getSelectedItem();
        if (selectedTransport != null) {
            boolean okClicked = main.showTransportEditDialog(selectedTransport);
            if (okClicked) {
                myApiSession.updateTransport(selectedTransport);
                showTransportDetails(selectedTransport);
                main.updateTransportTable();
                transportTable.setItems(main.getTransportData());
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
        transportTable.setItems(main.getTransportData());
    }
}
