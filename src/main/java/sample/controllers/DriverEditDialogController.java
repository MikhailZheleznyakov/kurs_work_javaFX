package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.models.Driver;

public class DriverEditDialogController {

    @FXML
    private TextField driverEditName;

    @FXML
    private TextField driverEditSurname;


    private Stage dialogStage;
    private Driver driver;
    private boolean okClicked = false;
    private Main main;

    @FXML
    private void initialize(){}

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    public void setDriver(Driver driver){
        this.driver = driver;
        driverEditName.setText(driver.getName());
        driverEditSurname.setText(driver.getSurname());
    }

    /**
     * Returns true, если пользователь кликнул OK, в другом случае false.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            driver.setName(driverEditName.getText());
            driver.setSurname(driverEditSurname.getText());
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

        if (driverEditName.getText() == null || driverEditName.getText().length() == 0) {
            errorMessage += "Введите имя!\n";
        }
        if (driverEditSurname.getText() == null || driverEditSurname.getText().length() == 0) {
            errorMessage += "Введите фамилию!\n";
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

    public void setMain(Main main) {
        this.main = main;
    }
}
