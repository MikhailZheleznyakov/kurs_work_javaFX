package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.controllers.*;
import sample.models.*;
import sample.utils.RestApi;

import java.io.File;
import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private RestApi myApiSession = new RestApi();
    private ObservableList<Client> clientData = FXCollections.observableArrayList();
    private ObservableList<Transport> transportData = FXCollections.observableArrayList();
    private ObservableList<Driver> driverData = FXCollections.observableArrayList();
    private ObservableList<Cargo> cargoData = FXCollections.observableArrayList();
    private ObservableList<Town> townData = FXCollections.observableArrayList();
    private ObservableList<Rate> rateData = FXCollections.observableArrayList();
    private ObservableList<Order> orderData = FXCollections.observableArrayList();


    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public BorderPane getRootLayout() {
        return rootLayout;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Курсовая");

        initRootLayout();
        showEntrance();


    }
    public void initRootLayout() {
        try{
            FXMLLoader loader = new FXMLLoader(new File("C:/Users/irong/Desktop/2 курс/JAVA/transportcargosFX/src/main/java/sample/views/rootLayout.fxml").toURI().toURL());
            rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showEntrance(){
        try {
            FXMLLoader loader = new FXMLLoader(new File("C:/Users/irong/Desktop/2 курс/JAVA/transportcargosFX/src/main/java/sample/views/entrance.fxml").toURI().toURL());
            AnchorPane client = (AnchorPane) loader.load();

            rootLayout.setCenter(client);

            EntranceController controller = loader.getController();
            controller.setMain(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateClientTable() {
        clientData.clear();
        //Читаем коллекцию персон с бека и обновляем ее
        clientData.addAll(myApiSession.getClient());
    }

    public void updateTransportTable() {
        transportData.clear();
        //Читаем коллекцию персон с бека и обновляем ее
        transportData.addAll(myApiSession.getTransport());
    }
    public void updateDriverTable() {
        driverData.clear();
        //Читаем коллекцию персон с бека и обновляем ее
        driverData = myApiSession.getDriver();
    }
    public void updateTownTable() {
        townData.clear();
        //Читаем коллекцию персон с бека и обновляем ее
        townData = myApiSession.getTown();
    }
    public void updateRateTable() {
        rateData.clear();
        //Читаем коллекцию персон с бека и обновляем ее
        rateData.addAll(myApiSession.getRate());
    }
    public void updateCargoTable() {
        cargoData.clear();
        //Читаем коллекцию персон с бека и обновляем ее
        cargoData.addAll(myApiSession.getCargo());
    }
    public void updateOrderTable() {
        orderData.clear();
        orderData.addAll(myApiSession.getOrder());
    }


    public void showOrder() {
        try {
            // Загружаем сведения об адресатах.
            FXMLLoader loader = new FXMLLoader(new File("C:/Users/irong/Desktop/2 курс/JAVA/transportcargosFX/src/main/java/sample/views/order.fxml").toURI().toURL());
            AnchorPane transport = (AnchorPane) loader.load();
            Scene scene = new Scene(transport);
            primaryStage.setScene(scene);

            // Даём контроллеру доступ к главному приложению.
            OrderController controller = loader.getController();
            controller.setMain(this);
            controller.updateTable();
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showClient() {
        try {
            // Загружаем сведения об адресатах.
            FXMLLoader loader = new FXMLLoader(new File("C:/Users/irong/Desktop/2 курс/JAVA/transportcargosFX/src/main/java/sample/views/client.fxml").toURI().toURL());

            AnchorPane client = (AnchorPane) loader.load();

            Scene scene = new Scene(client);
            primaryStage.setScene(scene);

            // Даём контроллеру доступ к главному приложению.
            ClientController controller = loader.getController();
            controller.setMain(this);
            controller.updateClient();
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showTransport() {
        try {
            // Загружаем сведения об адресатах.
            FXMLLoader loader = new FXMLLoader(new File("C:/Users/irong/Desktop/2 курс/JAVA/transportcargosFX/src/main/java/sample/views/transport.fxml").toURI().toURL());
            AnchorPane transport = (AnchorPane) loader.load();
            Scene scene = new Scene(transport);
            primaryStage.setScene(scene);

            // Даём контроллеру доступ к главному приложению.
            TransportController controller = loader.getController();
            controller.setMain(this);
            controller.updateTransport();
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showCargo() {
        try {
            // Загружаем сведения об адресатах.
            FXMLLoader loader = new FXMLLoader(new File("C:/Users/irong/Desktop/2 курс/JAVA/transportcargosFX/src/main/java/sample/views/cargo.fxml").toURI().toURL());
            AnchorPane transport = (AnchorPane) loader.load();
            Scene scene = new Scene(transport);
            primaryStage.setScene(scene);
            CargoController controller = loader.getController();
            controller.setMain(this);
            controller.addCargoData();

            // Даём контроллеру доступ к главному приложению.
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showTown() {
        try {
            // Загружаем сведения об адресатах.
            FXMLLoader loader = new FXMLLoader(new File("C:/Users/irong/Desktop/2 курс/JAVA/transportcargosFX/src/main/java/sample/views/town.fxml").toURI().toURL());
            AnchorPane transport = (AnchorPane) loader.load();
            Scene scene = new Scene(transport);
            primaryStage.setScene(scene);

            // Даём контроллеру доступ к главному приложению.
            TownController controller = loader.getController();
            controller.setMain(this);
            controller.updateTown();
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showRate() {
        try {
            // Загружаем сведения об адресатах.
            FXMLLoader loader = new FXMLLoader(new File("C:/Users/irong/Desktop/2 курс/JAVA/transportcargosFX/src/main/java/sample/views/rate.fxml").toURI().toURL());
            AnchorPane transport = (AnchorPane) loader.load();
            Scene scene = new Scene(transport);
            primaryStage.setScene(scene);

            // Даём контроллеру доступ к главному приложению.
            RateController controller = loader.getController();
            controller.setMain(this);
            controller.addRateData();
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showDriver() {
        try {
            // Загружаем сведения об адресатах.
            FXMLLoader loader = new FXMLLoader(new File("C:/Users/irong/Desktop/2 курс/JAVA/transportcargosFX/src/main/java/sample/views/driver.fxml").toURI().toURL());
            AnchorPane transport = (AnchorPane) loader.load();
            Scene scene = new Scene(transport);
            primaryStage.setScene(scene);

            // Даём контроллеру доступ к главному приложению.
            DriverController controller = loader.getController();
            controller.setMain(this);
            controller.addDriverData();
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void showMainpage(){
        try{
            FXMLLoader loader = new FXMLLoader(new File("C:/Users/irong/Desktop/2 курс/JAVA/transportcargosFX/src/main/java/sample/views/mainpage.fxml").toURI().toURL());
            AnchorPane mainpage = (AnchorPane) loader.load();
            Scene scene = new Scene(mainpage);
            primaryStage.setScene(scene);
            primaryStage.show();

            MainPageController controller = loader.getController();
            controller.setMain(this);

        }catch (IOException e){
            e.printStackTrace();
        }

    }



    /**
     * Конструктор
     */
    public Main() {
    }

    /**
     * Возвращает данные в виде наблюдаемого списка адресатов.
     * @return
     */
    public ObservableList<Client> getClientData() {
        return clientData;
    }

    public ObservableList<Transport> getTransportData() { return transportData; }

    public ObservableList<Driver> getDriverData() {
        return driverData;
    }

    public ObservableList<Rate> getRateData() {
        return rateData;
    }

    public ObservableList<Town> getTownData() {
        return townData;
    }

    public ObservableList<Cargo> getCargoData() {
        return cargoData;
    }

    public ObservableList<Order> getOrderData(){ return orderData; }

    public boolean showClientEditDialog(Client client) {
        try {
            // Загружаем fxml-файл и создаём новую сцену
            // для всплывающего диалогового окна.
            FXMLLoader loader = new FXMLLoader(new File("C:/Users/irong/Desktop/2 курс/JAVA/transportcargosFX/src/main/java/sample/views/clientEditDialog.fxml").toURI().toURL());
            AnchorPane page = (AnchorPane) loader.load();

            // Создаём диалоговое окно Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Передаём адресата в контроллер.
            ClientEditDialogController controllerEditDialog = loader.getController();
            controllerEditDialog.setDialogStage(dialogStage);
            controllerEditDialog.setClient(client);
            controllerEditDialog.setMain(this);

            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait();

            return controllerEditDialog.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showTransportEditDialog(Transport transport) {
        try {
            // Загружаем fxml-файл и создаём новую сцену
            // для всплывающего диалогового окна.
            FXMLLoader loader = new FXMLLoader(new File("C:/Users/irong/Desktop/2 курс/JAVA/transportcargosFX/src/main/java/sample/views/transportEditDialog.fxml").toURI().toURL());
            AnchorPane page = (AnchorPane) loader.load();

            // Создаём диалоговое окно Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Transport");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Передаём адресата в контроллер.
            TransportEditDialogController controllerEditDialog = loader.getController();
            controllerEditDialog.setDialogStage(dialogStage);
            controllerEditDialog.setTransport(transport);
            System.out.println(transport.toString());
            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait();

            return controllerEditDialog.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean showDriverEditDialog(Driver driver) {
        try {
            // Загружаем fxml-файл и создаём новую сцену
            // для всплывающего диалогового окна.
            FXMLLoader loader = new FXMLLoader(new File("C:/Users/irong/Desktop/2 курс/JAVA/transportcargosFX/src/main/java/sample/views/driverEditDialog.fxml").toURI().toURL());
            AnchorPane page = (AnchorPane) loader.load();

            // Создаём диалоговое окно Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Driver");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Передаём адресата в контроллер.
            DriverEditDialogController controllerEditDialog = loader.getController();
            controllerEditDialog.setDialogStage(dialogStage);
            controllerEditDialog.setDriver(driver);


            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait();

            return controllerEditDialog.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean showTownEditDialog(Town town) {
        try {
            // Загружаем fxml-файл и создаём новую сцену
            // для всплывающего диалогового окна.
            FXMLLoader loader = new FXMLLoader(new File("C:/Users/irong/Desktop/2 курс/JAVA/transportcargosFX/src/main/java/sample/views/townEditDialog.fxml").toURI().toURL());
            AnchorPane page = (AnchorPane) loader.load();

            // Создаём диалоговое окно Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Town");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Передаём адресата в контроллер.
            TownEditDialogController controllerEditDialog = loader.getController();
            controllerEditDialog.setDialogStage(dialogStage);
            controllerEditDialog.setTown(town);
            controllerEditDialog.setMain(this);

            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait();

            return controllerEditDialog.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean showRateEditDialog(Rate rate) {
        try {
            // Загружаем fxml-файл и создаём новую сцену
            // для всплывающего диалогового окна.
            FXMLLoader loader = new FXMLLoader(new File("C:/Users/irong/Desktop/2 курс/JAVA/transportcargosFX/src/main/java/sample/views/rateEditDialog.fxml").toURI().toURL());
            AnchorPane page = (AnchorPane) loader.load();

            // Создаём диалоговое окно Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Rate");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Передаём адресата в контроллер.
            RateEditDialogController controllerEditDialog = loader.getController();
            controllerEditDialog.setDialogStage(dialogStage);
            controllerEditDialog.setRate(rate);

            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait();

            return controllerEditDialog.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean showOrderEditDialog(Order order, OrderController orderController, boolean b) {
        try {
            // Загружаем fxml-файл и создаём новую сцену
            // для всплывающего диалогового окна.
            FXMLLoader loader = new FXMLLoader(new File("C:/Users/irong/Desktop/2 курс/JAVA/transportcargosFX/src/main/java/sample/views/orderEditDialog.fxml").toURI().toURL());
            AnchorPane page = (AnchorPane) loader.load();

            // Создаём диалоговое окно Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Transport");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Передаём адресата в контроллер.
            OrderEditDialogController controllerEditDialog = loader.getController();
            controllerEditDialog.setDialogStage(dialogStage);
            controllerEditDialog.setOrderController(orderController);
            controllerEditDialog.setFlag(b);
            controllerEditDialog.setOrder(order);
            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait();

            return controllerEditDialog.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showCargoEditDialog(Cargo cargo) {
        try {
            // Загружаем fxml-файл и создаём новую сцену
            // для всплывающего диалогового окна.
            FXMLLoader loader = new FXMLLoader(new File("C:/Users/irong/Desktop/2 курс/JAVA/transportcargosFX/src/main/java/sample/views/cargoEditDialog.fxml").toURI().toURL());
            AnchorPane page = (AnchorPane) loader.load();

            // Создаём диалоговое окно Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Transport");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Передаём адресата в контроллер.
            CargoEditController controllerEditDialog = loader.getController();
            controllerEditDialog.setDialogStage(dialogStage);
            controllerEditDialog.setCargo(cargo);
            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait();

            return controllerEditDialog.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public RestApi getMyApiSession() {
        return myApiSession;
    }

}
