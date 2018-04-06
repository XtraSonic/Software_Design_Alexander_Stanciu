/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.presentation;

import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.*;
import ro.utcluj.alexanderstanciu.sd.business.Interfaces.LogInValidator;

/**
 *
 * @author XtraSonic
 */
public class LogInSceneBuilder {
    
    private final Mediator mediator;
    
    public LogInSceneBuilder(Mediator mediator)
    {
        this.mediator  = mediator;
    }
    
    public void biuild(LogInValidator validator, Stage stage)
    {
        stage.setTitle("Ping Pong Tournaments App");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setPadding(new Insets(40, 40, 40, 40));
        Scene scene = new Scene(grid, 300, 300);

        Text logInText = new Text("Log In:");
        logInText.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        grid.add(logInText, 0, 0);

        Label emailLabel = new Label("User Email:");
        grid.add(emailLabel, 0, 1);

        TextField emailTextField = new TextField();
        grid.add(emailTextField, 1, 1);

        Label passwordLabel = new Label("Password:");
        grid.add(passwordLabel, 0, 2);

        PasswordField passwordField = new PasswordField();
        grid.add(passwordField, 1, 2);

        Button btn = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 3);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e)
            {
                String email = emailTextField.getText();
                String password = passwordField.getText();
                
                if(validator.logIn(email, password))
                {
                    stage.hide();
                    mediator.buildTournamentList();
                    
                }
                else
                {
                    actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("Invalid email or password!");
                }
            }
        });

        stage.setScene(scene);
        stage.show();
    }
        


}
