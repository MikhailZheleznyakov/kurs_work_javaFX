package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.Main;
import sample.models.Cargo;
import sample.models.Order;
import sample.models.Transport;
import sample.utils.RestApi;

public class OrderController {
    @FXML
    private TableView<Order> orderTable;
    @FXML
    private TableColumn<Order, Long> orderLoginColumn;
    @FXML
    private Label orderLoginLabel;
    @FXML
    private Label orderArrivalTownLabel;
    @FXML
    private Label orderDepartTownLabel;
    @FXML
    private Label orderDeliveryTypeLabel;
    @FXML
    private Label orderWeightLabel;
    @FXML
    private Label orderTransportLabel;
    @FXML
    private Label orderCostLabel;

    private Main main;

    private RestApi myApiSession = new RestApi();

    private ObservableList<Cargo> cargoData = myApiSession.getCargo();
    private ObservableList<Order> orderData = myApiSession.getOrder();

    public OrderController(){}


    public int getWeight(Long orderId){
        Integer sum = 0;
        for (Cargo i: cargoData){
            if (i.getOrder().getId()==orderId){
                sum+=i.getWeight();
            }
        }
        return sum;
    }

    public void showOrderDetails(Order order){
        if (order!=null){
            orderLoginLabel.setText(order.getClient_id().getLogin());
            orderArrivalTownLabel.setText(order.getArrivaltown_id().getName());
            orderDepartTownLabel.setText(order.getDeparttown_id().getName());
            orderDeliveryTypeLabel.setText(order.getDelivery_type());
            orderWeightLabel.setText(String.valueOf(getWeight(order.getId())));
            orderTransportLabel.setText(order.getTransport_id().getName());
            orderCostLabel.setText(String.valueOf(order.getCost()));
        }else{
            orderLoginLabel.setText("");
            orderArrivalTownLabel.setText("");
            orderDepartTownLabel.setText("");
            orderDeliveryTypeLabel.setText("");
            orderWeightLabel.setText("");
            orderTransportLabel.setText("");
            orderCostLabel.setText("");
        }
    }
    @FXML
    private void initialize(){
        orderLoginColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        showOrderDetails(null);
        orderTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showOrderDetails(newValue));
    }

    public void updateTable(){
        orderData = myApiSession.getOrder();
        ObservableList<Long> numberData = FXCollections.observableArrayList();
        for (Order i: orderData){
            numberData.add(i.getId());
        }
        this.orderTable.setItems(orderData);
    }

    @FXML
    private void handleDeleteOrder() {
        int selectedIndex = orderTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Long currentId = orderTable.getSelectionModel().getSelectedItem().getId();
            Order currentOrder = null;
            for (Order i: orderData){
                if (i.getId() == currentId){
                    currentOrder = i;
                }
            }
            if (currentOrder!=null){
                if (myApiSession.deleteOrder(currentOrder)) {
                    orderTable.getItems().remove(selectedIndex);
                }
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
    private void handleCreateOrder() {
        Order newOrder = new Order(-1L, null, null, null, null, null, 0);
        boolean okClicked = main.showOrderEditDialog(newOrder,this,true);
        if (okClicked) {
            myApiSession.createOrder(newOrder);
            main.updateOrderTable();
            updateTable();
        }
    }

    @FXML
    private void handleEditOrder() {
        Long selectedId = orderTable.getSelectionModel().getSelectedItem().getId();
        Order selectedOrder = null;
        for (Order i: orderData){
            if (i.getId() == selectedId){
                selectedOrder = i;
            }
        }
        if (selectedOrder != null) {
            boolean okClicked = main.showOrderEditDialog(selectedOrder,this,false);
            if (okClicked) {
                myApiSession.updateOrder(selectedOrder);
                showOrderDetails(selectedOrder);
                main.updateTransportTable();
                updateTable();
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
    }

}
