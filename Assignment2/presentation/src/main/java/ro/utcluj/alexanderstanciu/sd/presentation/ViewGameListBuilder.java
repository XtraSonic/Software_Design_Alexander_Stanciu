/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.presentation;

import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ro.utcluj.alexanderstanciu.sd.business.GameDetails;

/**
 *
 * @author XtraSonic
 */
class ViewGameListBuilder {
    private final Mediator mediator;

    public ViewGameListBuilder(Mediator mediator)
    {
        this.mediator = mediator;
    }
    
    public void biuild( List<GameDetails> games, String name,Stage stage)
    {
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setPadding(new Insets(40, 40, 40, 40));
        Scene scene = new Scene(grid, 500, 500);
        
        Text tournamentName = new Text("Tournament Name: " +name);
        tournamentName.setFont(Font.font("Times new roman", FontWeight.BOLD, 25));
        tournamentName.setUnderline(true);
        tournamentName.setOnMouseClicked(e->mediator.buildTournamentList());
        HBox hb = new HBox(10);
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().add(tournamentName);
        grid.add(hb ,0, 0, 8, 1);
        
        Text descriptionText = new Text("Player1 vs. Player2 (score1 - score2)");
        descriptionText.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        hb = new HBox(10);
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().add(descriptionText);
        grid.add(hb, 0, 1, 8, 1);
        
        Label buttonLabel;
        int levelIndexes[] = {1,1,1}; 
        for(GameDetails g : games)
        {
            buttonLabel = new Label(g.toString());
            buttonLabel.setOnMouseClicked(e ->
            {
                this.mediator.doAfterGameSelect(g.getId());
            });
            int level = g.getLevel();
            grid.add(buttonLabel, levelIndexes[level-1]++, level+1);
            
        };
        
        stage.setScene(scene);
        stage.show();
        
    }
}
