/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.view.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ro.utcluj.alexanderstanciu.sd.business.ModelController;

/**
 * FXML Controller class
 *
 * @author XtraSonic
 */
public class LoginController extends ViewController{

    @FXML
    private Label errorLabel;

    @FXML
    private Button loginButton;

    @FXML
    private TextField emailTextField;

    @FXML
    private PasswordField passwordField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        ModelController mc = ModelController.getInstance();
    }

    public void setError()
    {
        errorLabel.setText("Invalid email or password");
    }

    @FXML
    public void logIn(ActionEvent event) throws IOException
    {
        ModelController mc = ModelController.getInstance();
        if (mc.logIn(emailTextField.getText(), passwordField.getText()))
        {
            changeScene((Node)event.getSource(),"/fxml/TournamentView.fxml");
        }
        else
        {
            setError();
        }
    }

    @FXML
    public void clearError(ActionEvent event)
    {
        errorLabel.setText("");
    }

    @Override
    public void update(Observable o, Object o1)
    {
        
    }

}
