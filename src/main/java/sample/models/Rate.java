package sample.models;

import javafx.beans.property.*;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Rate implements APIModel{

    private final IntegerProperty costRate;

    private final LongProperty id;

    private Town arrivalTown;

    private Town departureTown;


    public Rate(Long id,Town arrivalTown, Town departureTown, Integer costRate) {
        this.id = new SimpleLongProperty(id);
        this.arrivalTown = arrivalTown;
        this.departureTown = departureTown;
        this.costRate = new SimpleIntegerProperty(costRate);
    }

    @Override
    public JSONObject toJson(){
        JSONObject map = new JSONObject();
        map.put("id", id.get());
        map.put("arrivalTown", arrivalTown.toJson());
        map.put("departureTown", departureTown.toJson());
        map.put("costRate", String.valueOf(costRate.getValue()));

        return map;
    }

    public int getCostRate() {
        return costRate.get();
    }

    public IntegerProperty costRateProperty() {
        return costRate;
    }

    public void setCostRate(int costRate) {
        this.costRate.set(costRate);
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

    public Town getArrivalTown() {
        return arrivalTown;
    }

    public void setArrivalTown(Town arrivalTown) {
        this.arrivalTown = arrivalTown;
    }

    public Town getDepartureTown() {
        return departureTown;
    }

    public void setDepartureTown(Town departureTown) {
        this.departureTown = departureTown;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "costRate=" + costRate +
                ", id=" + id +
                ", arrivalTown=" + arrivalTown +
                ", departureTown=" + departureTown +
                '}';
    }
}
