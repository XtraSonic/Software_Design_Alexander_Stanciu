/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.view.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import ro.utcluj.alexanderstanciu.sd.business.ModelController;
import ro.utcluj.alexanderstanciu.sd.dao.Entities.Match;

/**
 * FXML Controller class
 *
 * @author XtraSonic
 */
public class MatchViewController extends ViewController{

    @FXML private Label titleLabel;
    @FXML private VBox vbox;
    @FXML private Label errorLabel;
    
    private List<Label> listOfLabels = new ArrayList<>();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        ModelController mc = ModelController.getInstance();
        mc.addMatchObserver(this);
        mc.addGameObserver(this);
        setMatches();
    }    
    
    public void setMatches()
    {
        ModelController mc = ModelController.getInstance();
        List<Match> matches = mc.getMatchFromGame();
        titleLabel.setText(mc.getCurrentGameName());
        Label buttonLabel;
        for (Match m : matches)
        {
            buttonLabel = new Label(m.getPlayer1Score()
                                    + " -- "
                                    + m.getPlayer2Score());
            listOfLabels.add(buttonLabel);
            buttonLabel.setOnMouseClicked(e ->
            {
                MouseButton button = e.getButton();
                if(button == MouseButton.PRIMARY)
                    if(!mc.incrementMatchScore(m.getId()))
                        setError("You can't do that");
            });
            vbox.getChildren().add(buttonLabel);
        }
    }
    
    public void setError(String msg)
    {
        errorLabel.setText(msg);
    }
    
    @FXML
    public void returnToGame(MouseEvent event) throws IOException
    {
        ModelController mc = ModelController.getInstance();
        mc.deselectMatch();
        mc.removeMatchObserver(this);
        mc.removeGameObserver(this);
        changeScene((Node)event.getSource(), "/fxml/GameView.fxml");
    }

    @Override
    public void update(Observable o, Object o1)
    {
        listOfLabels.forEach(label-> vbox.getChildren().removeAll(label));
        setMatches();  
    }
    
}
