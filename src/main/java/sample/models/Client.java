package sample.models;

import javafx.beans.property.*;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Client implements APIModel{
    private final StringProperty name;
    private final StringProperty surname;
    private final StringProperty login;
    private final StringProperty phone;
    private final LongProperty id;

    public Client(Long id,String name, String surname, String login, String phone) {
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.login = new SimpleStringProperty(login);
        this.phone = new SimpleStringProperty(phone);

    }

    @Override
    public JSONObject toJson() {
        JSONObject map = new JSONObject();
        map.put("id", id.get());
        map.put("name", name.get());
        map.put("surname", surname.get());
        map.put("login", login.get());
        map.put("phone", phone.get());

        return map;
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

    public String getLogin() {
        return login.get();
    }

    public StringProperty loginProperty() {
        return login;
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
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

    @Override
    public String toString() {
        return "Client{" +
                "name=" + name +
                ", surname=" + surname +
                ", login=" + login +
                ", phone=" + phone +
                ", id=" + id +
                '}';
    }
}
