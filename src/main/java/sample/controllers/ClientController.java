package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Main;
import sample.models.Client;
import sample.utils.RestApi;

public class ClientController {
    @FXML
    private TableView<Client> clientTable;

    @FXML
    private TableColumn<Client, String> clientFirstNameColumn;

    @FXML
    private TableColumn<Client, String> clientLastNameColumn;

    @FXML
    private Label clientFirstNameLabel;

    @FXML
    private Label clientLastNameLabel;

    @FXML
    private Label clientLoginLabel;

    @FXML
    private Label clientPhoneLabel;

    @FXML
    private TextField clientFilter;

    private Main main;

    private Stage clientStage;

    private Client client;

    private RestApi myApiSession = new RestApi();

    public ClientController(){}

    private void showClientDetails(Client client) {
        if (client != null) {
            clientFirstNameLabel.setText(client.getName());
            clientLastNameLabel.setText(client.getSurname());
            clientLoginLabel.setText(client.getLogin());
            clientPhoneLabel.setText(client.getPhone());
        } else {
            clientFirstNameLabel.setText("");
            clientLastNameLabel.setText("");
            clientLoginLabel.setText("");
            clientPhoneLabel.setText("");
        }
    }


    @FXML
    private void initialize() {
        clientFirstNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        clientLastNameColumn.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());
        showClientDetails(null);
        clientTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showClientDetails(newValue));
    }


    public void updateClient(){
        main.updateClientTable();
    }

    @FXML
    private void handleDeleteClient() {
        int selectedIndex = clientTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Client currentClient = clientTable.getItems().get(selectedIndex);
            if (myApiSession.deleteClient(currentClient)) {
                clientTable.getItems().remove(selectedIndex);
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
    private void handleNewClient() {
        Client newClient = new Client(-1L, null, null, null, null);
        boolean okClicked = main.showClientEditDialog(newClient);
        if (okClicked) {
            myApiSession.createClient(newClient);
            main.updateClientTable();
        }
    }

    @FXML
    private void handleEditClient() {
        Client selectedClient = clientTable.getSelectionModel().getSelectedItem();

        System.out.println(selectedClient.getId());
        if (selectedClient != null) {
            boolean okClicked = main.showClientEditDialog(selectedClient);
            if (okClicked) {
                myApiSession.updateClient(selectedClient);
                showClientDetails(selectedClient);
                main.updateClientTable();
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
    private void handleCancel(){main.showMainpage();}

    public void setMain(Main main){
        this.main = main;
        clientTable.setItems(main.getClientData());
    }

}
