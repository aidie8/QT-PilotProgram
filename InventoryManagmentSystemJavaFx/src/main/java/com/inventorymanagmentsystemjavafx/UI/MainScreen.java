package com.inventorymanagmentsystemjavafx.UI;

import com.inventorymanagmentsystemjavafx.ApplicationController;
import com.inventorymanagmentsystemjavafx.UI.Controllers.MainScreenController;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import javafx.stage.Stage;

public class MainScreen implements Screen {

    private TabPane pane;

    private MainScreenController controller;
    private final Stage stage;


    private NewOrderScreen newOrderScreen;
    public MainScreen(Stage stage)
    {

        this.stage = stage;
        pane = new TabPane();
        controller = new MainScreenController();
        pane.setMinSize(600,400);
        Tab summaryScreenTab = new Tab("Summary");
        summaryScreenTab.closableProperty().set(false);
        Tab newOrderScreenTab = new Tab("New Order");


        newOrderScreenTab.closableProperty().set(false);
        this.newOrderScreen = new NewOrderScreen();

        newOrderScreenTab.setContent(this.newOrderScreen.getPane());
        Tab statisticScreenTab = new Tab("Statistics");
        statisticScreenTab.closableProperty().set(false);
        Tab inventoryScreenTab = new Tab("Inventory");
        inventoryScreenTab.closableProperty().set(false);

        pane.tabMinWidthProperty().bind(pane.widthProperty().divide(4).subtract(15));
        pane.tabMaxWidthProperty().bind(pane.widthProperty().divide(4).subtract(15));


        pane.getTabs().addAll(summaryScreenTab,newOrderScreenTab,statisticScreenTab,inventoryScreenTab);
    }

    @Override
    public Parent getPane() {
        return pane;
    }
}
