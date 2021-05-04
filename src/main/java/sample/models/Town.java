package sample.models;


import javafx.beans.property.*;
import org.json.JSONObject;


public class Town implements APIModel{
    private final LongProperty id;
    private final StringProperty name;
    private final StringProperty info;



    public Town(Long id,String name, String info){
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);
        this.info = new SimpleStringProperty(info);
    }

    @Override
    public JSONObject toJson() {
        JSONObject map = new JSONObject();
        map.put("id", id.get());
        map.put("name", name.get());
        map.put("info", info.get());

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

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getInfo() {
        return info.get();
    }

    public StringProperty infoProperty() {
        return info;
    }

    public void setInfo(String info) {
        this.info.set(info);
    }

    @Override
    public String toString() {
        return "Town{" +
                "id=" + id +
                ", name=" + name +
                ", info=" + info +
                '}';
    }
}
