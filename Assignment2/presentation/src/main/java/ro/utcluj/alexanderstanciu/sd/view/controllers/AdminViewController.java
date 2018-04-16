/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.view.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ro.utcluj.alexanderstanciu.sd.business.ModelController;
import ro.utcluj.alexanderstanciu.sd.dao.Entities.Tournament;
import ro.utcluj.alexanderstanciu.sd.dao.Entities.User;

/**
 * FXML Controller class
 *
 * @author XtraSonic
 */
public class AdminViewController extends ViewController {

    @FXML private TextField tournamentNameTF;
    @FXML private TextField tournamentStartDateTF;
    @FXML private TextField tournamentFeeTF;
    @FXML private ComboBox<User> userComboBox;
    @FXML private TextField moneyUpdateAmmountTF;
    @FXML private ComboBox<Tournament> gameTournamentCB;
    @FXML private ComboBox<User> gamePlayer1CB;
    @FXML private ComboBox<User> gamePlayer2CB;
    @FXML private TextField gameLevelTF;

    @FXML
    void createGame(ActionEvent event)
    {
        ModelController mc = ModelController.getInstance();
        Tournament t = gameTournamentCB.getSelectionModel().getSelectedItem();
        User u1 = gamePlayer1CB.getSelectionModel().getSelectedItem();
        User u2 = gamePlayer2CB.getSelectionModel().getSelectedItem();
        int level = Integer.parseInt(gameLevelTF.getText());
        mc.createNewGame(t,u1,u2,level);
    }

    @FXML
    void createTournament(ActionEvent event)
    {
        ModelController mc = ModelController.getInstance();
        mc.createNewTournament(tournamentNameTF.getText(),
                               LocalDate.parse(tournamentStartDateTF.getText()),
                               Integer.parseInt(tournamentFeeTF.getText()));
    }

    @FXML
    void updateUserMoney(ActionEvent event)
    {
        User u = userComboBox.getSelectionModel().getSelectedItem();
        if (u == null)
        {
            return;
        }
        ModelController mc = ModelController.getInstance();
        String s = moneyUpdateAmmountTF.getText();
        int ammount = Integer.parseInt(s);
        mc.updateUserMoney(u, ammount);

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        ModelController mc = ModelController.getInstance();
        List<User> listUsers = mc.getAllUsers();
        userComboBox.getItems().addAll(listUsers);
        gameTournamentCB.getItems().addAll(mc.getAllTournaments());
    }
    
    @FXML
    public void setUsers(ActionEvent event)
    {
        ModelController mc = ModelController.getInstance();
        Tournament t = gameTournamentCB.getSelectionModel().getSelectedItem();
        List<User> listUsers = t.getParticipants();
        gamePlayer1CB.getItems().addAll(listUsers);
        gamePlayer2CB.getItems().addAll(listUsers);
    }

    public void returnToTournamentsView(ActionEvent event) throws IOException
    {
        changeScene((Node) event.getSource(), "/fxml/TournamentView.fxml");
    }

    @Override
    public void update(Observable o, Object o1)
    {
        throw new UnsupportedOperationException("Not supported yet.");
        //TODO
    }
}
