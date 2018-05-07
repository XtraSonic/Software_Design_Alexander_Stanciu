/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.webnews.GUI.controllers;

import java.io.IOException;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author XtraSonic
 */
public abstract class ViewController implements Initializable, Observer {

    protected void changeScene(Node source, String fxmlPath)
    {
        try
        {
            Parent parent = FXMLLoader.load(getClass().getResource(fxmlPath));
            Scene view = new Scene(parent);
            Stage window = (Stage) (source).getScene().getWindow();
            Platform.runLater(() ->
            {
                window.setScene(view);
                window.show();
            });

        }
        catch (IOException ex)
        {
            Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
