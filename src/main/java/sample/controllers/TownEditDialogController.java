package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.models.Town;

public class TownEditDialogController {
    @FXML
    private TextField townEditName;

    @FXML
    private TextField townEditInfo;

    private Stage dialogStage;
    private Town town;
    private boolean okClicked = false;
    private Main main;

    @FXML
    private void initialize(){}

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setTown(Town town) {
        this.town = town;
        townEditName.setText(town.getName());
        townEditInfo.setText(town.getInfo());
    }

    public void setMain(Main main) {
        this.main = main;
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
            town.setName(townEditName.getText());
            town.setInfo(townEditInfo.getText());
            //Обновляем модель
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

        if (townEditName.getText() == null || townEditName.getText().length() == 0) {
            errorMessage += "Введите название города!\n";
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

