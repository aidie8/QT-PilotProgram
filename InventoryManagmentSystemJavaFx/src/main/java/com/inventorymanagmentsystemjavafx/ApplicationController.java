package com.inventorymanagmentsystemjavafx;

import com.inventorymanagmentsystemjavafx.Backend.DatabaseManager;
import com.inventorymanagmentsystemjavafx.UI.LoginScreen;
import com.inventorymanagmentsystemjavafx.UI.MainScreen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ApplicationController extends Application {

    LoginScreen loginScreen;
    MainScreen mainScreen;

    DatabaseManager manager;
    Scene baseScene;
    public static ApplicationController INSTANCE;
    @Override
    public void start(Stage stage){
        INSTANCE = this;
        loginScreen = new LoginScreen(stage);
        manager = new DatabaseManager();
        mainScreen = new MainScreen(stage);

        baseScene = new Scene(loginScreen.getPane());

        stage.setTitle("Inventory Management");
        stage.setScene(baseScene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }


    public MainScreen getMainScreen() {
        return mainScreen;
    }

    public LoginScreen getLoginScreen() {
        return loginScreen;
    }


    public void Login(String username, String password)
    {

        if(manager.Connect(username,password))
        {
            baseScene.setRoot(mainScreen.getPane());
            manager.loadInventory();
        }
    }




    public DatabaseManager getManager()
    {
        return manager;

    }



}