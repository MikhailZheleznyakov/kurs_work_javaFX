package sample.models;

import javafx.beans.property.*;
import org.json.JSONObject;

public class Transport implements APIModel{
    private final StringProperty name;
    private final IntegerProperty capacity;
    private final IntegerProperty wearout;
    private Driver driver;
    private final StringProperty transport_type;
    private final LongProperty id;

    public Transport(Long id, String name, Integer capacity, Integer wearout, Driver driver, String transport_type){
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);
        this.capacity = new SimpleIntegerProperty(capacity);
        this.wearout = new SimpleIntegerProperty(wearout);
        this.driver = driver;
        this.transport_type = new SimpleStringProperty(transport_type);
    }

    @Override
    public JSONObject toJson(){
        JSONObject map = new JSONObject();
        map.put("id", id.get());
        map.put("name", name.get());
        map.put("capacity", String.valueOf(capacity.getValue()));
        map.put("wearout", String.valueOf(wearout.getValue()));
        map.put("driver", driver.toJson());
        map.put("transport_type", transport_type.get());

        return map;
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

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getCapacity() {
        return capacity.get();
    }

    public void setCapacity(int capacity) {
        this.capacity.set(capacity);
    }

    public void setWearout(int wearout) {
        this.wearout.set(wearout);
    }

    public int getWearout() {
        return wearout.get();
    }

    public IntegerProperty capacityProperty() {
        return capacity;
    }

    public IntegerProperty wearoutProperty() {
        return wearout;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getTransport_type() {
        return transport_type.get();
    }

    public StringProperty transport_typeProperty() {
        return transport_type;
    }

    public void setTransport_type(String transport_type) {
        this.transport_type.set(transport_type);
    }

    @Override
    public String toString() {
        return "Transport{" +
                "name=" + name +
                ", capacity=" + capacity +
                ", wearout=" + wearout +
                ", driver=" + driver +
                ", transport_type=" + transport_type +
                ", id=" + id +
                '}';
    }
}
