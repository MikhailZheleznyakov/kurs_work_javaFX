package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.models.Rate;
import sample.models.Town;
import sample.utils.RestApi;

public class RateEditDialogController {
    @FXML
    private ChoiceBox<Town> rateEditArrivalTown;

    @FXML
    private ChoiceBox<Town> rateEditDepartureTown;

    @FXML
    private TextField rateEditCost;

    private Stage dialogStage;
    private Rate rate;
    private boolean okClicked = false;
    private Main main;
    private RestApi myApiSession = new RestApi();
    private ObservableList<Town> townData = FXCollections.observableArrayList();

    @FXML
    private void choiceTown(){
        townData = myApiSession.getTown();
        rateEditArrivalTown.setItems(townData);
        rateEditDepartureTown.setItems(townData);
    }
    @FXML
    private void initialize(){
        choiceTown();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setRate(Rate rate){
        this.rate = rate;
        rateEditArrivalTown.setValue(rateEditArrivalTown.getValue());
        rateEditDepartureTown.setValue(rateEditDepartureTown.getValue());
        rateEditCost.setText(String.valueOf(rate.getCostRate()));
    }
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            rate.setArrivalTown(rateEditArrivalTown.getValue());
            rate.setDepartureTown(rateEditDepartureTown.getValue());
            rate.setCostRate(Integer.parseInt(rateEditCost.getText()));

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
