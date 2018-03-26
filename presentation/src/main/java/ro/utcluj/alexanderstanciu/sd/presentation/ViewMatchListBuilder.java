/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.presentation;

import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ro.utcluj.alexanderstanciu.sd.business.Entities.Match;

/**
 *
 * @author XtraSonic
 */
class ViewMatchListBuilder {
    private Mediator mediator;

    public ViewMatchListBuilder(Mediator mediator)
    {
        this.mediator = mediator;
    }
    
    public void build( List<Match> matches,String gameName, Stage stage)
    {
        stage.setTitle("Match List");

        VBox vb = new VBox(20);
        vb.setAlignment(Pos.CENTER);

        Text titleText = new Text(gameName);
        titleText.setFont(Font.font("Times new roman", FontWeight.BOLD, 25));
        titleText.setUnderline(true);
        titleText.setOnMouseClicked(e->mediator.buildGameList());
        vb.getChildren().add(titleText);
        
        Label buttonLabel;
        for (Match m : matches)
        {
            buttonLabel = new Label(m.getPlayer1_score()
                                    + " -- "
                                    + m.getPlayer2_score());
            buttonLabel.setOnMouseClicked(e ->
            {
                MouseButton button = e.getButton();
                if(button == MouseButton.PRIMARY)
                    mediator.incremetMatchScore(m.getId());
            });
            vb.getChildren().add(buttonLabel);
        }

        Scene scene = new Scene(vb, 300, 300);
        stage.setScene(scene);
        stage.show();
    }
    
    
}
