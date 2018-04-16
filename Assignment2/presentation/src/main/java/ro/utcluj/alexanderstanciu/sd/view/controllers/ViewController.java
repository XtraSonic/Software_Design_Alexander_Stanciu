/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.view.controllers;

import java.io.IOException;
import java.util.Observer;
import javafx.event.ActionEvent;
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

    protected void changeScene(Node source, String fxmlPath) throws IOException
    {
        Parent parent = FXMLLoader.load(getClass().getResource(fxmlPath));
        Scene tournamentsViewScene = new Scene(parent);
        Stage window = (Stage)(source).getScene().getWindow();
        window.setScene(tournamentsViewScene);
        window.show();
    }
}
