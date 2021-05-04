package sample.models;

import javafx.beans.property.*;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Driver implements APIModel{
    private final StringProperty name;
    private final StringProperty surname;
    private final LongProperty id;

    public Driver(Long id, String name, String surname){
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
    }

    @Override
    public JSONObject toJson() {

        JSONObject map = new JSONObject();
        map.put("id", id.getValue());
        map.put("name", name.get());
        map.put("surname", surname.get());
        return map;
    }
    public String getViewDriver(){
        return name.get() + " " + surname.get();
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

    public String getSurname() {
        return surname.get();
    }

    public StringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
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

    @Override
    public String toString() {
        return "Driver{" +
                "name=" + name +
                ", surname=" + surname +
                ", id=" + id +
                '}';
    }
}
