package sample.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import sample.models.*;

import java.util.ArrayList;
import java.util.List;

public class RestApi {
    private static final String ServerURL = "http://localhost:8282";
    /**
     * Создание клиента
     * Использует POST
     *
     * @param client
     */

    public void createClient(Client client) {
        HttpClass.PostRequest(ServerURL + "/client/addClient", client.toJson());
    }


    public void createTown(Town town){HttpClass.PostRequest(ServerURL + "/town/addTown", town.toJson());}

    public void createDriver(Driver driver){
        HttpClass.PostRequest(ServerURL + "/driver/addDriver", driver.toJson());}
    /**
     * Создание клиента
     * Использует POST
     *
     * @param transport
     */
    public void createTransport(Transport transport){
        HttpClass.PostRequest(ServerURL + "/transport/addTransport", transport.toJson());}
    /**
     * Создание клиента
     * Использует POST
     *
     * @param rate
     */
    public void createRate(Rate rate){
        HttpClass.PostRequest(ServerURL + "/rate/addRate", rate.toJson());}
    /**
     * Создание клиента
     * Использует POST
     *
     * @param cargo
     */
    public void createCargo(Cargo cargo){
        HttpClass.PostRequest(ServerURL + "/cargo/addCargo", cargo.toJson());}
    /**
     * Создание клиента
     * Использует POST
     *
     * @param order
     */
    public void createOrder(Order order){
        HttpClass.PostRequest(ServerURL + "/order/addOrder", order.toJson());}
    /**
     * Получение персоны по опеределенному id
     * Использет GET
     * @param id
     * @return
     */
    //public Person GetPerson(String id){

    //}

    /**
     * Получение всех персон
     * Использет GET
     *
     * @return
     */
    public ObservableList<Client> getClient() {
        ObservableList<Client> result = FXCollections.observableArrayList();
        String buffer = HttpClass.GetRequest(ServerURL + "/client/getAllClient");

        if (buffer!=null) {
            JSONArray jsonResult = new JSONArray(buffer);

            for (int i = 0; i < jsonResult.length(); i++) {
                JSONObject currentClient = jsonResult.getJSONObject(i);

                String name = currentClient.getString("name");
                String surname = currentClient.getString("surname");
                String login = currentClient.getString("login");
                String phone = currentClient.getString("phone");
                Long id = currentClient.getLong("id");

                Client newClient = new Client(id, name, surname, login, phone);
                result.add(newClient);
            }
        }
        return result;
    }
    public ObservableList<Town> getTown() {
        ObservableList<Town> result = FXCollections.observableArrayList();
        String buffer = HttpClass.GetRequest(ServerURL + "/town/getAllTown");

        if (buffer!=null) {
            JSONArray jsonResult = new JSONArray(buffer);

            for (int i = 0; i < jsonResult.length(); i++) {
                JSONObject currentTown = jsonResult.getJSONObject(i);
                Long id = currentTown.getLong("id");
                String name = currentTown.getString("name");
                String info = null;
                try {
                    info = currentTown.getString("info");
                } catch (JSONException ignored){

                }
                Town newTown = new Town(id, name, info);
                result.add(newTown);
            }
        }
        return result;
    }
    /**
     * Получение всех транспортов
     * Использет GET
     *
     * @return
     */
    public ObservableList<Rate> getRate() {
        ObservableList<Rate> result = FXCollections.observableArrayList();

        String buffer = HttpClass.GetRequest(ServerURL + "/rate/getAllRate");

        if (buffer!=null) {
            JSONArray jsonResult = new JSONArray(buffer);


            for (int i = 0; i < jsonResult.length(); i++) {
                JSONObject currentRate = jsonResult.getJSONObject(i);
                Long id = currentRate.getLong("id");
                JSONObject arrivaltown_id = currentRate.getJSONObject("arrivaltown");
                JSONObject departtown_id = currentRate.getJSONObject("departtown");
                Town newArrivalTown = null;
                try{
                    newArrivalTown = new Town(arrivaltown_id.getLong("id"),
                            arrivaltown_id.getString("name"),
                            arrivaltown_id.getString("info"));
                }catch (JSONException e) {
                    newArrivalTown = new Town(arrivaltown_id.getLong("id"),
                            arrivaltown_id.getString("name"),
                            null);
                }
                Town newDepartTown = null;
                try{
                    newDepartTown = new Town(departtown_id.getLong("id"),
                            departtown_id.getString("name"),
                            departtown_id.getString("info"));
                }catch (JSONException e) {
                    newDepartTown = new Town(departtown_id.getLong("id"),
                            departtown_id.getString("name"),
                            null);
                }

                Integer cost = currentRate.getInt("cost");
                Rate newRate = new Rate(id, newArrivalTown, newDepartTown, cost);
                result.add(newRate);
            }

        }
        return result;
    }

    /**
     * Получение всех транспортов
     * Использет GET
     *
     * @return
     */
    public ObservableList<Transport> getTransport() {
        ObservableList<Transport> result = FXCollections.observableArrayList();

        String buffer = HttpClass.GetRequest(ServerURL + "/transport/getAllTransport");

        if (buffer!=null) {
            JSONArray jsonResult = new JSONArray(buffer);


            for (int i = 0; i < jsonResult.length(); i++) {
                JSONObject currentTransport = jsonResult.getJSONObject(i);

                String name = currentTransport.getString("name");
                Integer capacity = currentTransport.getInt("capacity");
                Integer wearout = currentTransport.getInt("wearout");
                JSONObject driver = currentTransport.getJSONObject("driver");
                Driver newDriver = new Driver(driver.getLong("id"), driver.getString("name"), driver.getString("surname"));
                String transport_type = currentTransport.getString("transport_type");
                Long id = currentTransport.getLong("id");

                Transport newTransport = new Transport(id, name, capacity, wearout, newDriver, transport_type);
                result.add(newTransport);
            }

        }
        return result;
    }

    public ObservableList<Driver> getDriver(){
        ObservableList<Driver> result = FXCollections.observableArrayList();

        String buffer = HttpClass.GetRequest(ServerURL + "/driver/getAllDriver");

        if (buffer!=null) {
            JSONArray jsonResult = new JSONArray(buffer);

            for (int i = 0; i < jsonResult.length(); i++) {
                JSONObject currentDriver = jsonResult.getJSONObject(i);

                Long id = currentDriver.getLong("id");
                String name = currentDriver.getString("name");
                String surname = currentDriver.getString("surname");
                Driver newDriver = new Driver(id, name, surname);
                result.add(newDriver);

            }
        }
        return result;
    }
    public ObservableList<Cargo> getCargo(){
        ObservableList<Cargo> result = FXCollections.observableArrayList();

        String buffer = HttpClass.GetRequest(ServerURL + "/cargo/getAllCargo");

        if (buffer!=null) {
            JSONArray jsonResult = new JSONArray(buffer);

            for (int i = 0; i < jsonResult.length(); i++) {
                JSONObject currentCargo = jsonResult.getJSONObject(i);

                Long id = currentCargo.getLong("id");
                String name = currentCargo.getString("name");
                Integer weight = currentCargo.getInt("weight");
                JSONObject order_id = currentCargo.getJSONObject("order");
                JSONObject client_id = order_id.getJSONObject("client_id");
                JSONObject arrivaltown_id = order_id.getJSONObject("arrivaltown");
                JSONObject departtown_id = order_id.getJSONObject("departtown");
                JSONObject transport_id = order_id.getJSONObject("transport");
                JSONObject driver = transport_id.getJSONObject("driver");
                Town newTown = null;
                try{
                    newTown = new Town(arrivaltown_id.getLong("id"),
                            arrivaltown_id.getString("name"),
                            arrivaltown_id.getString("info"));
                }catch (JSONException e){
                    newTown = new Town(arrivaltown_id.getLong("id"),
                            arrivaltown_id.getString("name"),
                            null);
                }
                Town newTown2 = null;
                try{
                    newTown2 = new Town(departtown_id.getLong("id"),
                            departtown_id.getString("name"),
                            departtown_id.getString("info"));
                }catch (JSONException e){
                    newTown2 = new Town(departtown_id.getLong("id"),
                            departtown_id.getString("name"),
                            null);
                }
                Order newOrderCargo = new Order(order_id.getLong("id"),
                        new Client(client_id.getLong("id"),
                                client_id.getString("name"),
                                client_id.getString("surname"),
                                client_id.getString("login"),
                                client_id.getString("phone")),
                        newTown,
                        newTown2,
                        new Transport(transport_id.getLong("id"),
                                transport_id.getString("name"),
                                transport_id.getInt("capacity"),
                                transport_id.getInt("wearout"),
                                new Driver(driver.getLong("id"),
                                        driver.getString("name"),
                                        driver.getString("surname")),
                                transport_id.getString("transport_type")),
                        order_id.getString("delivery_type"),
                        order_id.getInt("cost"));
                Cargo newCargo = new Cargo(id,name,weight,newOrderCargo);
                result.add(newCargo);

            }
        }
        return result;
    }
    public ObservableList<Order> getOrder() {
        ObservableList<Order> result = FXCollections.observableArrayList();

        String buffer = HttpClass.GetRequest(ServerURL + "/order/getAllOrder");

        if (buffer != null) {
            JSONArray jsonResult = new JSONArray(buffer);

            for (int i = 0; i < jsonResult.length(); i++) {
                JSONObject currentOrder = jsonResult.getJSONObject(i);
                Long id = currentOrder.getLong("id");
                try {
                    JSONObject client_id = currentOrder.getJSONObject("client_id");
                    JSONObject arrivaltown_id = currentOrder.getJSONObject("arrivaltown");
                    JSONObject departtown_id = currentOrder.getJSONObject("departtown");
                    JSONObject transport_id = currentOrder.getJSONObject("transport");
                    JSONObject driver = transport_id.getJSONObject("driver");
                    Client newClient = new Client(client_id.getLong("id"),
                            client_id.getString("name"),
                            client_id.getString("surname"),
                            client_id.getString("login"),
                            client_id.getString("phone"));

                    Town newArrivalTown = null;
                    try{
                        newArrivalTown = new Town(arrivaltown_id.getLong("id"),
                                arrivaltown_id.getString("name"),
                                arrivaltown_id.getString("info"));
                    }catch (JSONException e) {
                        newArrivalTown = new Town(arrivaltown_id.getLong("id"),
                                arrivaltown_id.getString("name"),
                                null);
                    }
                    Town newDepartTown = null;
                    try{
                        newDepartTown = new Town(departtown_id.getLong("id"),
                                departtown_id.getString("name"),
                                departtown_id.getString("info"));
                    }catch (JSONException e) {
                        newDepartTown = new Town(departtown_id.getLong("id"),
                                departtown_id.getString("name"),
                                null);
                    }
                    Transport newTransport = new Transport(transport_id.getLong("id"),
                            transport_id.getString("name"),
                            transport_id.getInt("capacity"),
                            transport_id.getInt("wearout"),
                            new Driver(driver.getLong("id"),
                                    driver.getString("name"),
                                    driver.getString("surname")),
                            transport_id.getString("transport_type"));
                    String delivery_type = currentOrder.getString("delivery_type");
                    Integer cost = currentOrder.getInt("cost");
                    Order newOrder = new Order(id, newClient, newArrivalTown, newDepartTown, newTransport, delivery_type, cost);
                    result.add(newOrder);
                }catch (JSONException e){

                }
            }
        } return result;
    }

    /**
     * Обновление персоны
     * Использует PUT
     *
     * @param client
     */
    public void updateClient(Client client) {
        Long id = client.getId();
        HttpClass.PutRequest(ServerURL + "/client/updateClient/" + id, client.toJson());
    }
    /**
     * Обновление персоны
     * Использует PUT
     *
     * @param driver
     */
    public void updateDriver(Driver driver) {
        Long id = driver.getId();
        HttpClass.PutRequest(ServerURL + "/driver/updateDriver/" + id, driver.toJson());
    }
    /**
     * Обновление транспорта
     * Использует PUT
     *
     * @param transport
     */
    public void updateTransport(Transport transport) {
        Long id = transport.getId();
        HttpClass.PutRequest(ServerURL + "/transport/updateTransport/" + id, transport.toJson());
    }
    /**
     * Обновление тарифа
     * Использует PUT
     *
     * @param rate
     */
    public void updateRate(Rate rate) {
        Long id = rate.getId();
        HttpClass.PutRequest(ServerURL + "/transport/updateTransport/" + id, rate.toJson());
    }
    /**
     * Обновление города
     * Использует PUT
     *
     * @param town
     */
    public void updateTown(Town town) {
        Long id = town.getId();
        HttpClass.PutRequest(ServerURL + "/town/updateTown/" + id, town.toJson());
    }
    /**
     * Обновление города
     * Использует PUT
     *
     * @param cargo
     */
    public void updateCargo(Cargo cargo) {
        Long id = cargo.getId();
        HttpClass.PutRequest(ServerURL + "/cargo/updateCargo/" + id, cargo.toJson());
    }
    /**
     * Обновление заказа
     * Использует PUT
     *
     * @param order
     */
    public void updateOrder(Order order) {
        Long id = order.getId();
        HttpClass.PutRequest(ServerURL + "/order/updateOrder/" + id, order.toJson());
    }

    /**
     * Удаление персоны.
     * Использует DELETE
     *
     * @param client
     */
    public boolean deleteClient(Client client) {
        Long id = client.getId();
        if (id == null)
            return false;
        return HttpClass.DeleteRequest(ServerURL + "/client/deleteClient/" + id);
    }

    public boolean deleteTown(Town town) {
        Long id = town.getId();
        if (id == null)
            return false;
        return HttpClass.DeleteRequest(ServerURL + "/town/deleteTown/" + id);
    }
    /**
     * Удаление тарифа.
     * Использует DELETE
     *
     * @param rate
     */
    public boolean deleteRate(Rate rate) {
        Long id = rate.getId();
        if (id == null)
            return false;
        return HttpClass.DeleteRequest(ServerURL + "/rate/deleteRate/" + id);
    }

    /**
     * Удаление персоны.
     * Использует DELETE
     *
     * @param transport
     */
    public boolean deleteTransport(Transport transport) {
        Long id = transport.getId();
        if (id == null)
            return false;
        return HttpClass.DeleteRequest(ServerURL + "/transport/deleteTransport/" + id);
    }
    /**
     * Удаление персоны.
     * Использует DELETE
     *
     * @param order
     */
    public boolean deleteOrder(Order order) {
        Long id = order.getId();
        if (id == null)
            return false;
        return HttpClass.DeleteRequest(ServerURL + "/order/deleteOrder/" + id);
    }
    /**
     * Удаление персоны.
     * Использует DELETE
     *
     * @param cargo
     */
    public boolean deleteCargo(Cargo cargo) {
        Long id = cargo.getId();
        if (id == null)
            return false;
        return HttpClass.DeleteRequest(ServerURL + "/cargo/deleteCargo/" + id);
    }
}
