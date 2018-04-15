/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.view.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ro.utcluj.alexanderstanciu.sd.business.ModelController;
import ro.utcluj.alexanderstanciu.sd.dao.Entities.Tournament;

/**
 * FXML Controller class
 *
 * @author XtraSonic
 */
public class TournamentViewController implements Initializable, Observer {

    @FXML private TableView<Tournament> tableView;
    @FXML private TableColumn<T
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        populateTable();
    }    

    @Override
    public void update(Observable o, Object o1)
    {
        populateTable();
    }

    private void populateTable()
    {
        ModelController mc = ModelController.getInstance();
        //TODO
    }
    
    @FXML
    public void logOut(ActionEvent event) throws IOException
    {
        ModelController mc = ModelController.getInstance();
        mc.logOut();
        changeScene(event, "/fxml/LogIn.fxml");
        
    }
    
    private void changeScene(ActionEvent event,String fxmlPath) throws IOException
    {
        Parent parent = FXMLLoader.load(getClass().getResource(fxmlPath));
        Scene tournamentsViewScene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tournamentsViewScene);
        window.show();
    }
}
