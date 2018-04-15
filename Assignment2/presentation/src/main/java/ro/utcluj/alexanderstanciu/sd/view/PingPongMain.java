/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.view;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ro.utcluj.alexanderstanciu.sd.business.ModelController;

/**
 *
 * @author XtraSonic
 */
public class PingPongMain extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException
    {
        URL url = this.getClass().getResource("/fxml/LogIn.fxml");
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("Ping Pong Tournament Manager");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setOnCloseRequest((WindowEvent event) ->
        {
            ModelController.getInstance().close();
        });
    }

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
    
}
