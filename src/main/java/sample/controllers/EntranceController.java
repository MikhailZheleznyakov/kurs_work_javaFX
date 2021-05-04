package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.Main;

import java.awt.*;

public class EntranceController {

    @FXML
    private Label loginEntranceLabel;

    @FXML
    private Label passwordEntranceLabel;

    @FXML
    private TextField loginEntranceText;

    @FXML
    private PasswordField passwordEntranceText;

    private Main main;

    @FXML
    private void handleEntrance(){
        main.showMainpage();
    }

    public Label getLoginEntranceLabel() {
        return loginEntranceLabel;
    }

    public Label getPasswordEntranceLabel() {
        return passwordEntranceLabel;
    }

    public TextField getLoginEntranceText() {
        return loginEntranceText;
    }

    public PasswordField getPasswordEntranceText() {
        return passwordEntranceText;
    }

    public Main getMain() {
        return main;
    }

    public void setLoginEntranceLabel(Label loginEntranceLabel) {
        this.loginEntranceLabel = loginEntranceLabel;
    }

    public void setPasswordEntranceLabel(Label passwordEntranceLabel) {
        this.passwordEntranceLabel = passwordEntranceLabel;
    }

    public void setLoginEntranceText(TextField loginEntranceText) {
        this.loginEntranceText = loginEntranceText;
    }

    public void setPasswordEntranceText(PasswordField passwordEntranceText) {
        this.passwordEntranceText = passwordEntranceText;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    private boolean clicked = false;
}
