package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.models.*;
import sample.utils.RestApi;

public class OrderEditDialogController {
    @FXML
    private ChoiceBox<String> orderEditLogin;
    @FXML
    private ChoiceBox<String> orderEditArrivalTown;
    @FXML
    private ChoiceBox<String> orderEditDepartTown;
    @FXML
    private ChoiceBox<String> orderEditDeliveryType;
    @FXML
    private TextField orderEditWeight;
    @FXML
    private ChoiceBox<String> orderEditTransport;
    @FXML
    private TextField orderEditCost;

    private Stage dialogStage;

    private Order order;

    private boolean okClicked = false;

    private Main main;

    private RestApi myApiSession = new RestApi();

    private ObservableList<Transport> transportData = FXCollections.observableArrayList();
    private ObservableList<Client> clientData = FXCollections.observableArrayList();
    private ObservableList<Town> townData = FXCollections.observableArrayList();
    private ObservableList<Order> orderData = FXCollections.observableArrayList();
    private OrderController orderController;
    private boolean b;

    public void updateDeliveryType(){
        ObservableList<String> deliveryType = FXCollections.observableArrayList();
        deliveryType.add("Стандарт");
        deliveryType.add("Стандарт +");
        deliveryType.add("Экспресс");
        orderEditDeliveryType.setItems(deliveryType);
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void initialize(){
        orderEditWeight.setDisable(true);
        orderEditCost.setDisable(true);
    }

    public void setOrder(Order order){
        this.order = order;
        clientData = myApiSession.getClient();
        ObservableList<String> viewClient = FXCollections.observableArrayList();
        clientData = myApiSession.getClient();
        for (Client i: clientData){
            viewClient.add(i.getLogin());
        }
        orderEditLogin.setItems(viewClient);
        for (Client i: clientData){
            if (i.getLogin().equals(order.getClient_id())){
                orderEditLogin.setValue(i.getLogin());
                break;
            }
        }
        ObservableList<String> viewTown = FXCollections.observableArrayList();
        townData = myApiSession.getTown();
        for (Town i: townData){
            viewTown.add(i.getName());
        }
        orderEditArrivalTown.setItems(viewTown);
        for (Town i: townData){
            if (i.getName().equals(order.getArrivaltown_id())){
                orderEditArrivalTown.setValue(i.getName());
                break;
            }
        }
        orderEditDepartTown.setItems(viewTown);
        for (Town i: townData){
            if (i.getName().equals(order.getDeparttown_id())){
                orderEditDepartTown.setValue(i.getName());
                break;
            }
        }
        updateDeliveryType();
        for (Order i: orderData){
            if (i.getDelivery_type().equals(order.getDelivery_type())){
                orderEditDeliveryType.setValue(i.getDelivery_type());
                break;
            }
        }
        ObservableList<String> viewTransport = FXCollections.observableArrayList();
        transportData = myApiSession.getTransport();
        for (Transport i: transportData){
            viewTransport.add(i.getName());
        }

        orderEditWeight.setText(String.valueOf(0));

        orderEditTransport.setItems(viewTransport);
        for (Transport i: transportData){
            if (i.getName().equals(order.getTransport_id())){
                orderEditTransport.setValue(i.getName());
                break;
            }
        }
        if (!b){
            setCurrentOrder(order);
        }
        if (orderEditDeliveryType.getValue() == "Стандарт +"){
            orderEditCost.setText(String.valueOf(1000));
        }
    }

    public void setOrderController(OrderController orderController){
        this.orderController = orderController;
    }
    public void setCurrentOrder(Order order){
        this.order = order;
        orderEditLogin.setValue(order.getClient_id().getLogin());
        orderEditArrivalTown.setValue(order.getArrivaltown_id().getName());
        orderEditDepartTown.setValue(order.getDeparttown_id().getName());
        orderEditDeliveryType.setValue(order.getDelivery_type());
        orderEditWeight.setText(String.valueOf(orderController.getWeight(order.getId())));
        orderEditTransport.setValue(order.getTransport_id().getName());
        orderEditCost.setText(String.valueOf(order.getCost()));
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            System.out.println(orderEditCost.getText());
            System.out.println(order.getCost());
            for (Client i: clientData){
                if (orderEditLogin.getValue().equals(i.getLogin())){
                    order.setClient_id(i);
                    break;
                }
            }
            for (Town i: townData){
                if(orderEditArrivalTown.getValue().equals(i.getName())){
                    order.setArrivaltown_id(i);
                }
            }
            for (Town i: townData){
                if(orderEditDepartTown.getValue().equals(i.getName())){
                    order.setDeparttown_id(i);
                }
            }

            order.setDelivery_type(orderEditDeliveryType.getValue());

            for (Transport i: transportData){
                if(orderEditTransport.getValue().equals(i.getName())){
                    order.setTransport_id(i);
                }
            }

            if (!orderEditCost.getText().equals("")){
                order.setCost(Integer.parseInt(orderEditCost.getText()));
            }else{
                order.setCost(0);
            }

            ObservableList<Rate> newRate = myApiSession.getRate();
            System.out.println(newRate);
            for (Rate i: newRate){
                if ((i.getArrivalTown().getName().equals(order.getArrivaltown_id().getName()))&(i.getDepartureTown().getName().equals(order.getDeparttown_id().getName()))){
                    order.setCost(order.getCost()+i.getCostRate());
                    orderEditCost.setText(String.valueOf(order.getCost()+i.getCostRate()));
                    System.out.println(order.getCost());
                }
            }
            System.out.println(orderEditCost.getText());
            System.out.println(order.getCost());

            System.out.println(order);
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

    public void setFlag(boolean b) {
        this.b = b;
    }
}
