/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.webnews.GUI;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ro.utcluj.alexanderstanciu.sd.webnews.client.Client;

/**
 *
 * @author XtraSonic
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException
    {
        Client client = Client.getInstance();
        client.connect();
        URL url = this.getClass().getResource("/fxml/ArticlesView.fxml");
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("News Articles");
        primaryStage.setScene(new Scene(root));
        primaryStage.setX(0);
        primaryStage.setY(0);
        primaryStage.show();
        primaryStage.setOnCloseRequest((WindowEvent event) ->
        {
            client.close();
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
