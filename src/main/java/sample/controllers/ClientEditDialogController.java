package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.models.Client;
import sample.utils.RestApi;

public class ClientEditDialogController {
    @FXML
    private TextField clientEditFirstName;

    @FXML
    private TextField clientEditLastName;

    @FXML
    private TextField clientEditLogin;

    @FXML
    private TextField clientEditPhone;

    private Stage dialogStage;
    private Client client;
    private boolean okClicked = false;
    private Main main;
    private RestApi myApiSession = new RestApi();

    @FXML
    private void initialize(){}

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setMyApiSession(RestApi myApiSession) {
        this.myApiSession = myApiSession;
    }

    public void setClient(Client client){
        this.client = client;
        clientEditFirstName.setText(client.getName());
        clientEditLastName.setText(client.getSurname());
        clientEditLogin.setText(client.getLogin());
        clientEditPhone.setText(client.getPhone());
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
            System.out.println(this.client);
            client.setName(clientEditFirstName.getText());
            client.setSurname(clientEditLastName.getText());
            client.setLogin(clientEditLogin.getText());
            client.setPhone(clientEditPhone.getText());
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

        if (clientEditFirstName.getText() == null || clientEditFirstName.getText().length() == 0) {
            errorMessage += "Введите имя!\n";
        }
        if (clientEditLastName.getText() == null || clientEditLastName.getText().length() == 0) {
            errorMessage += "Введите фамилию!\n";
        }
        if (clientEditLogin.getText() == null || clientEditLogin.getText().length() == 0) {
            errorMessage += "Введите Ваш login!\n";
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
