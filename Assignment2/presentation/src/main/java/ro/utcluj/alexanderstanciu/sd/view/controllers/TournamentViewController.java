/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.view.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ro.utcluj.alexanderstanciu.sd.business.ModelController;
import ro.utcluj.alexanderstanciu.sd.dao.Entities.Tournament;

/**
 * FXML Controller class
 *
 * @author XtraSonic
 */
public class TournamentViewController extends ViewController {
    
    @FXML private TableView<Tournament> tableView;
    @FXML private TableColumn<Tournament, String> nameColumn;
    @FXML private TableColumn<Tournament, LocalDate> startDateColumn;
    @FXML private TableColumn<Tournament, Integer> prizeColumn;
    @FXML private TableColumn<Tournament, Integer> feeColumn;
    @FXML private Label errorLabel;
    @FXML private Label balanceLabel;
    @FXML private CheckBox freeFilter;
    @FXML private CheckBox paidFilter;
    @FXML private CheckBox enrolledFilter;
    @FXML private CheckBox upcomingFilter;
    @FXML private CheckBox ongoingFilter;
    @FXML private CheckBox finishedFilter;
    @FXML private Button adminButton;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        prizeColumn.setCellValueFactory(new PropertyValueFactory<>("prizePool"));
        feeColumn.setCellValueFactory(new PropertyValueFactory<>("fee"));
        
        ModelController mc = ModelController.getInstance();
        mc.addUserObserver(this);
        mc.addTournamentObserver(this);
        if (mc.getAdminStatus())
        {
            adminButton.setVisible(true);
        }
        
        populateTable();
    }
    
    @Override
    public void update(Observable o, Object o1)
    {
        populateTable();
    }
    
    @FXML
    private void populateTable()
    {
        ModelController mc = ModelController.getInstance();
        balanceLabel.setText(Integer.toString(mc.getUserBalance()));
        tableView.setItems(getObservableTournaments());
    }
    
    private ObservableList<Tournament> getObservableTournaments()
    {
        ObservableList<Tournament> tournaments = FXCollections.observableArrayList();
        ModelController mc = ModelController.getInstance();
        mc.getAllTournaments()
                .stream()
                .filter(tournament ->
                {
                    return checkShowFree(tournament)
                           && checkShowPaid(tournament)
                           && checkOnlyEnrolled(tournament)
                           && checkOnlyUpcoming(tournament)
                           && checkShowOngoing(tournament)
                           && checkShowFinished(tournament);
                })
                .forEach(t -> tournaments.add(t));
        return tournaments;
    }
    
    private boolean checkShowFree(Tournament t)
    {
        if (freeFilter.isSelected())
        {
            return true;
        }
        else
        {
            return t.getFee() != 0;
        }
        
    }
    
    private boolean checkShowPaid(Tournament t)
    {
        if (paidFilter.isSelected())
        {
            return true;
        }
        else
        {
            return t.getFee() == 0;
        }
    }
    
    private boolean checkShowOngoing(Tournament t)
    {
        if (ongoingFilter.isSelected())
        {
            return true;
        }
        else
        {
            ModelController mc = ModelController.getInstance();
            return mc.hasTournamentEnded(t);
        }
    }
    
    private boolean checkShowFinished(Tournament t)
    {
        if (finishedFilter.isSelected())
        {
            return true;
        }
        else
        {
            ModelController mc = ModelController.getInstance();
            return !mc.hasTournamentEnded(t);
        }
    }
    
    private boolean checkOnlyEnrolled(Tournament t)
    {
        ModelController mc = ModelController.getInstance();
        if (enrolledFilter.isSelected())
        {
            return mc.isEnrolled(t);
        }
        else
        {
            return true;
        }
    }
    
    private boolean checkOnlyUpcoming(Tournament t)
    {
        if (upcomingFilter.isSelected())
        {
            return t.getStartDate().isAfter(LocalDate.now());
        }
        else
        {
            return true;
        }
    }
    
    @FXML
    public void logOut(ActionEvent event) throws IOException
    {
        ModelController mc = ModelController.getInstance();
        mc.logOut();
        changeScene((Node) event.getSource(), "/fxml/LogIn.fxml");
        
    }
    
    @FXML
    public void selectTournament(ActionEvent event) throws IOException
    {
        ModelController mc = ModelController.getInstance();
        Tournament tournament = tableView.getSelectionModel().getSelectedItem();
        if (tournament == null)
        {
            setError("No tournament was selected!");
            return;
        }
        mc.setCurrentTournament(tournament);
        changeScene((Node) event.getSource(), "/fxml/GameView.fxml");
    }
    
    public void setError(String message)
    {
        errorLabel.setText(message);
    }
    
    @FXML
    public void clearError(MouseEvent event)
    {
        errorLabel.setText("");
    }
    
    @FXML
    public void enroll(ActionEvent event)
    {
        Tournament tournament = tableView.getSelectionModel().getSelectedItem();
        if (tournament == null)
        {
            setError("No tournament was selected!");
            return;
        }
        ModelController mc = ModelController.getInstance();
        mc.setCurrentTournament(tournament);
        setError(mc.enroll());
        mc.deselectTournament();
        
    }
    
    @FXML
    public void goToAdminPage(ActionEvent event) throws IOException
    {
        changeScene((Node) event.getSource(), "/fxml/AdminView.fxml");
    }
    
}
