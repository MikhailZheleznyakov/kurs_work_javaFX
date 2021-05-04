package sample.controllers;

import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.models.Driver;
import sample.models.Transport;
import sample.utils.RestApi;

public class TransportEditDialogController {
    @FXML
    private TextField transportEditName;

    @FXML
    private TextField transportEditCapacity;

    @FXML
    private TextField transportEditWearout;

    @FXML
    private ChoiceBox<String> transportEditDriver;

    @FXML
    private ChoiceBox<String> transportEditTransportType;

    private Stage dialogStage;
    private Transport transport;
    private boolean okClicked = false;
    private Main main;
    private RestApi myApiSession = new RestApi();
    private ObservableList<Driver> driverData = FXCollections.observableArrayList();
    private ObservableList<Transport> chtransportType = myApiSession.getTransport();

    public void updateTransportType(){
//        transportEditTransportType.setItems(chtransportType);
        ObservableList<String> transportType = FXCollections.observableArrayList();
//        for (Transport i: chtransportType){
//            if (!transportType.contains(i.getTransport_type())){
//                transportType.add(i.getTransport_type());
//            }
//        }
        transportType.add("Легковой автомобиль");
        transportType.add("Грузовой автомобиль");
        transportType.add("Самолет");
        transportType.add("Корабль");
        transportEditTransportType.setItems(transportType);
    }



    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    private void initialize(){

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setTransport(Transport transport){
        updateTransportType();
        this.transport = transport;
        transportEditName.setText(transport.getName());
        transportEditCapacity.setText(String.valueOf(transport.getCapacity()));
        transportEditWearout.setText(String.valueOf(transport.getWearout()));
        ObservableList<String> viewDriver = FXCollections.observableArrayList();
        driverData = myApiSession.getDriver();
        for (Driver i: driverData){
            viewDriver.add(i.getViewDriver());
        }
        transportEditDriver.setItems(viewDriver);
        for (Driver i: driverData){
            if ((i.getName() + " " + i.getSurname()).equals(transport.getDriver().getName() + " " + transport.getDriver().getSurname())){
                transportEditDriver.setValue(i.getName() + " " + i.getSurname());
                break;
            }
        }
        for (Transport i: chtransportType){
            if (i.getTransport_type().equals(transport.getTransport_type())){
                transportEditTransportType.setValue(i.getTransport_type());
                break;
            }
        }
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            transport.setName(transportEditName.getText());
            transport.setCapacity(Integer.parseInt(transportEditCapacity.getText()));
            transport.setWearout(Integer.parseInt(transportEditWearout.getText()));
            for (Driver i: driverData){
                if (transportEditDriver.getValue().equals(i.getName() + " " + i.getSurname())){
                    transport.setDriver(i);
                    break;
                }
            }
            transport.setTransport_type(transportEditTransportType.getValue());

            okClicked = true;
            dialogStage.close();
        }
    }
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (transportEditName.getText() == null || transportEditName.getText().length() == 0) {
            errorMessage += "Введите имя транспорта!\n";
        }
        if (transportEditCapacity.getText() == null || transportEditCapacity.getText().length() == 0) {
            errorMessage += "Введите грузоподъемность!\n";
        }
        if (transportEditWearout.getText() == null || transportEditWearout.getText().length() == 0) {
            errorMessage += "Введите процент износа!\n";
        }
        if (transportEditDriver.getValue() == null){
            errorMessage += "Введите водителя!\n";
        }
        if (transportEditTransportType.getValue() == null || transportEditTransportType.getValue().length() == 0){
            errorMessage += "Введите тип транспорта!\n";
        }


        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Неверно введены некоторые поля");
            alert.setHeaderText("Введите поля корректно");
            alert.setContentText(errorMessage);

            alert.showAndWait();
            return false;
        }
    }
}
