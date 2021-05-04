package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.Main;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class MainPageController {

    private Main main;
    private AnchorPane rootLayout;
    private Stage primaryStage = new Stage();
    @FXML
    private MenuItem clientMenu;

    @FXML
    public void handleMainClient(){

        main.showClient();
    }

    @FXML
    public void handleMainTransport(){
        main.showTransport();
    }

    @FXML
    public void handleMainDriver(){
        main.showDriver();
    }

    @FXML
    public void handleMainRate(){
        main.showRate();
    }

    @FXML
    public void handleMainOrder(){ main.showOrder();}

    @FXML
    public void handleMainTown(){ main.showTown(); }


    @FXML
    public void handleMainCargo(){main.showCargo();}

    public Main getMain() {
        return main;
    }


    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    public  void initialize(){

    }
}
