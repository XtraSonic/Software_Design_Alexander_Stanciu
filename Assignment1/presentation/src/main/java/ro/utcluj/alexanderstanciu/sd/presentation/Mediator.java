/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.presentation;

import java.time.LocalDate;
import javafx.application.Application;
import javafx.stage.Stage;
import ro.utcluj.alexanderstanciu.sd.business.Controller;

/**
 *
 * @author XtraSonic
 */
public class Mediator extends Application {

    private Stage primaryStage = null;
    private LogInSceneBuilder logInScene = null;
    private final ViewTournamentListBuilder viewTournamentListBuilder;
    private final Controller controller;
    private final ViewGameListBuilder viewGameListBuilder;
    private final ViewMatchListBuilder viewMatchListBuilder;
   
    public Mediator()
    {
        super();
        controller = new Controller();
        logInScene = new LogInSceneBuilder(this);
        viewTournamentListBuilder = new ViewTournamentListBuilder(this);
        viewGameListBuilder = new ViewGameListBuilder(this);
        viewMatchListBuilder = new ViewMatchListBuilder(this);
    }

    @Override
    public void start(Stage stage)
    {
        this.primaryStage = stage;
        buildLogIn();
    }

    public void buildLogIn()
    {
        System.out.println("Building Log In");
        logInScene.biuild(controller.getUserSession(), primaryStage);
        System.out.println("Done building Log In");
    }
    
    public void buildTournamentList()
    {
        System.out.println("Building Tournaments");
        viewTournamentListBuilder.build(controller.getAllTournaments(),
                                        primaryStage,
                                        controller.getAdminStatus());
        System.out.println("Done building Tournaments");
    }

    public void buildGameList()
    {
        System.out.println("Building Games");
        viewGameListBuilder.biuild(controller.getGamesFromTournament(),
                                   controller.getTournamentName(),
                                   primaryStage);
        
        System.out.println("Done building Games");
    }
    public void doAfterTournamentSelect(int id)
    {
        System.out.println("Tournament id selected: " + id);
        controller.setTournamentById(id);
        buildGameList();
        
    }

    
    
    public void buildMatchList()
    {
        System.out.println("Building Matches");
        viewMatchListBuilder.build(controller.getMatchFromGame(),
                                   controller.getCurrentGameName(),
                                   primaryStage);
        
        System.out.println("Done building Matches");
    }
    public void doAfterGameSelect(int id)
    {
        System.out.println("Game id selected: "+  id);
        controller.setGameById(id);
        buildMatchList();
    }

    
    public void incremetMatchScore(int id)
    {
        if(controller.incrementMatchScore(id))
        {
            buildMatchList();
        }
        
        else
        {
            System.err.println("This User can`t increment here");
        }
        
    }
    
   

    void createTournament(String name, LocalDate date, int prize)
    {
        controller.createNewTournament(name,date,prize);
        buildTournamentList();
    }

    void createUser(String email, String password, boolean admin)
    {
        controller.createNewUser(email,password,admin);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
    }
}
