package sample.models;

import javafx.beans.property.*;
import org.json.JSONObject;

import java.util.List;

public class Order implements APIModel{

    private final StringProperty delivery_type;

    private final IntegerProperty cost;

    private final LongProperty id;

    private Client client_id;

    private Town arrivaltown_id;

    private Town departtown_id;

    private Transport transport_id;

    private Cargo cargo;

    public Order(long id, Client client_id, Town arrivaltown_id, Town departtown_id, Transport transport_id, String delivery_type, Integer cost) {
        this.id = new SimpleLongProperty(id);
        this.client_id = client_id;
        this.arrivaltown_id = arrivaltown_id;
        this.departtown_id = departtown_id;
        this.transport_id = transport_id;
        this.delivery_type = new SimpleStringProperty(delivery_type);
        this.cost = new SimpleIntegerProperty(cost);
    }

    @Override
    public JSONObject toJson(){
        JSONObject map = new JSONObject();
        map.put("id", id.get());
        map.put("client_id", client_id.toJson());
        map.put("arrivaltown", arrivaltown_id.toJson());
        map.put("departtown", departtown_id.toJson());
        map.put("transport", transport_id.toJson());
        map.put("delivery_type", delivery_type.get());
        map.put("cost", cost.get());
        return map;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public String getDelivery_type() {
        return delivery_type.get();
    }

    public StringProperty delivery_typeProperty() {
        return delivery_type;
    }

    public void setDelivery_type(String delivery_type) {
        this.delivery_type.set(delivery_type);
    }

    public int getCost() {
        return cost.get();
    }

    public IntegerProperty costProperty() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost.set(cost);
    }

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public Client getClient_id() {
        return client_id;
    }

    public void setClient_id(Client client_id) {
        this.client_id = client_id;
    }

    public Town getArrivaltown_id() {
        return arrivaltown_id;
    }

    public void setArrivaltown_id(Town arrivaltown_id) {
        this.arrivaltown_id = arrivaltown_id;
    }

    public Town getDeparttown_id() {
        return departtown_id;
    }

    public void setDeparttown_id(Town departtown_id) {
        this.departtown_id = departtown_id;
    }

    public Transport getTransport_id() {
        return transport_id;
    }

    public void setTransport_id(Transport transport_id) {
        this.transport_id = transport_id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "delivery_type=" + delivery_type +
                ", cost=" + cost +
                ", id=" + id +
                ", client_id=" + client_id +
                ", arrivaltown_id=" + arrivaltown_id +
                ", departtown_id=" + departtown_id +
                ", transport_id=" + transport_id +
                '}';
    }
}

