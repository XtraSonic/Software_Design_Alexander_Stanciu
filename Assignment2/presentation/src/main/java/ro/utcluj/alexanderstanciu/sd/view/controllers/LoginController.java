/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.view.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author XtraSonic
 */
public class LoginController implements Initializable {

    @FXML
    private Label errorLabel;
    
    @FXML
    private Button logInButton;
    
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
        
        // TODO
    }    
    
    public void setError()
    {
        errorLabel.setText("Invalid email or password");
    }
    
    @FXML
    public void logIn(ActionEvent event)
    {
        System.out.println(emailTextField.getText());
        System.out.println(passwordField.getText());
    }
}
