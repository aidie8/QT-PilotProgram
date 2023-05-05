package com.inventorymanagmentsystemjavafx.UI;


import com.inventorymanagmentsystemjavafx.ApplicationController;
import com.inventorymanagmentsystemjavafx.UI.Controllers.LoginScreenController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LoginScreen implements Screen {
    private Pane pane;

    private LoginScreenController controller;
    private final Stage stage;
    public LoginScreen(Stage stage) {
        this.stage = stage;
        controller = new LoginScreenController();
        pane = new AnchorPane();
        pane.setMinSize(600,400);
        HBox mainHBox = new HBox();
        HBox usernameHBox = new HBox();
        HBox passwordHBox = new HBox();

        VBox mainVBox = new VBox();
        Button changeScene = new Button("Login");


        TextField username = new TextField();
        PasswordField field = new PasswordField();
        username.setFont(new Font(20));
        field.setFont(new Font(20));

        changeScene.setOnMousePressed(e -> ApplicationController.INSTANCE.Login(username.getText(),field.getText()));

        Label usernameLabel = new Label("Username");
        Label passwordLabel = new Label("Password");
        usernameLabel.setFont(new Font(20));
        passwordLabel.setFont(new Font(20));
        HBox.setMargin(usernameLabel,new Insets(0,40,0,0));
        HBox.setMargin(passwordLabel,new Insets(0,40,0,0));

        usernameHBox.getChildren().add(usernameLabel);
        passwordHBox.getChildren().add(passwordLabel);


        VBox.setMargin(username,new Insets(20,80,20,80));
        VBox.setMargin(field,new Insets(20,80,20,80));
        VBox.setMargin(changeScene,new Insets(20,0,40,0));

        field.setPrefSize(200,25);
        username.setPrefSize(200,25);

        mainHBox.setAlignment(Pos.CENTER);
        mainHBox.getChildren().add(mainVBox);
        //mainHBox.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));

        //mainVBox.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        mainVBox.setAlignment(Pos.CENTER);

        usernameHBox.getChildren().add(username);
        usernameHBox.setAlignment(Pos.CENTER);
        //usernameHBox.setBackground(new Background(new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY)));

        passwordHBox.getChildren().add(field);
        passwordHBox.setAlignment(Pos.CENTER);
        //passwordHBox.setBackground(new Background(new BackgroundFill(Color.ORANGERED, CornerRadii.EMPTY, Insets.EMPTY)));


        VBox.setMargin(passwordHBox,new Insets(0,40,20,40));
        VBox.setMargin(usernameHBox,new Insets(0,40,20,40));
        mainVBox.getChildren().add(usernameHBox);
        mainVBox.getChildren().add(passwordHBox);
        mainVBox.getChildren().add(changeScene);
        mainVBox.prefWidthProperty().bind(mainHBox.widthProperty().multiply(0.75));
        pane.getChildren().add(mainHBox);
        AnchorPane.setTopAnchor(mainHBox,50d);
        AnchorPane.setBottomAnchor(mainHBox,50d);
        AnchorPane.setRightAnchor(mainHBox,20d);
        AnchorPane.setLeftAnchor(mainHBox,20d);

    }


    @Override
    public Parent getPane() {
        return pane;
    }

}
