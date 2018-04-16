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
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import ro.utcluj.alexanderstanciu.sd.business.GameDetails;
import ro.utcluj.alexanderstanciu.sd.business.ModelController;

/**
 * FXML Controller class
 *
 * @author XtraSonic
 */
public class GameViewController extends ViewController{

    @FXML private GridPane gridView;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        setLabels();
    }
    
    private void setLabels()
    {
        ModelController mc = ModelController.getInstance();
        List<GameDetails> gameList=  mc.getGamesFromCurrentTournament();
        Label buttonLabel;
        int levelIndexes[] = {0,2,3}; 
        for(GameDetails g : gameList)
        {
            buttonLabel = new Label(g.toString());
            buttonLabel.setOnMouseClicked(e ->
            {
                try
                {
                    selectGame(e,g.getId());
                }
                catch (IOException ex)
                {
                    Logger.getLogger(GameViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            int level = g.getLevel();
            gridView.add(buttonLabel, levelIndexes[level-1], level-1);
            levelIndexes[level-1]+=2;
        };
        
    }

    public void selectGame(MouseEvent event, int id) throws IOException
    {
        ModelController mc = ModelController.getInstance();
        mc.setGameById(id);
        changeScene((Node)event.getSource(), "/fxml/MatchView.fxml");
    }
    
    public void returnToTournaments(ActionEvent event) throws IOException
    {
        
        ModelController mc = ModelController.getInstance();
        mc.deselectTournament();
        changeScene((Node)event.getSource(), "/fxml/TournamentView.fxml");
    }
    
    @Override
    public void update(Observable o, Object o1)
    {
        setLabels();
    }
    
}
