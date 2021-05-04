package sample.models;

import javafx.beans.property.*;
import org.json.JSONObject;

public class Cargo implements APIModel{
    private final LongProperty id;
    private final StringProperty name;
    private final IntegerProperty weight;
    private Order order;

    public Cargo(Long id, String name, Integer weight, Order order) {
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);
        this.weight = new SimpleIntegerProperty(weight);
        this.order = order;
    }

    @Override
    public JSONObject toJson(){
        JSONObject map = new JSONObject();
        map.put("id", id.get());
        map.put("name", name.get());
        map.put("weight", name.get());
        map.put("order", order.toJson());
        return map;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getWeight() {
        return weight.get();
    }

    public IntegerProperty weightProperty() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight.set(weight);
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "id=" + id +
                ", name=" + name +
                ", weight=" + weight +
                '}';
    }
}
