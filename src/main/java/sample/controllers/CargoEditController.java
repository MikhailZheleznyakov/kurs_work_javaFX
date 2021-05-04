package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.models.Cargo;
import sample.models.Rate;
import sample.utils.RestApi;

public class CargoEditController {
    @FXML
    private TextField cargoEditName;

    @FXML
    private TextField cargoEditWeight;

    @FXML
    private ChoiceBox<String> cargoEditOrder;

    private Stage dialogStage;
    private Cargo cargo;
    private boolean okClicked = false;
    private Main main;
    private RestApi myApiSession = new RestApi();

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public void setMain(Main main) {
        this.main = main;
    }
    public boolean isOkClicked() {
        return okClicked;
    }
}
