/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.presentation;

import java.time.LocalDate;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ro.utcluj.alexanderstanciu.sd.dao.Entities.Tournament;

/**
 *
 * @author XtraSonic
 */
class ViewTournamentListBuilder {

    private final Mediator mediator;

    public ViewTournamentListBuilder(Mediator meditator)
    {
        this.mediator = meditator;
    }

    public void build(List<Tournament> tournaments, Stage stage, boolean admin)
    {
        stage.setTitle("Tournaments");

        VBox vb = new VBox(20);
        vb.setAlignment(Pos.CENTER);

        Text titleText = new Text("Tournament List");
        titleText.setFont(Font.font("Times new roman", FontWeight.BOLD, 25));
        titleText.setUnderline(true);
        titleText.setOnMouseClicked(e -> mediator.buildLogIn());
        vb.getChildren().add(titleText);

        Text descriptionText = new Text("Name -- Date --  Prize");
        descriptionText.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        vb.getChildren().add(descriptionText);

        Label buttonLabel;

        for (Tournament t : tournaments)
        {
            buttonLabel = new Label(t.getName()
                                    + " -- "
                                    + t.getDate().toString()
                                    + " -- "
                                    + t.getPrize_pool());
            buttonLabel.setOnMouseClicked(e ->
            {
                mediator.doAfterTournamentSelect(t.getId());
            });
            vb.getChildren().add(buttonLabel);
        }

        if (admin)
        {
            GridPane grid = new GridPane();

            grid.setHgap(15);
            grid.setVgap(15);
            grid.setPadding(new Insets(20, 20, 20, 20));

            TextField nameTF = new TextField();
            nameTF.setPromptText("Tournament Name");
            grid.add(nameTF, 0, 0);

            TextField dateTF = new TextField();
            dateTF.setPromptText("Tournament Date");
            grid.add(dateTF, 1, 0);

            TextField prizeTF = new TextField();
            prizeTF.setPromptText("Tournament prize");
            grid.add(prizeTF, 2, 0);

            Button createTournament = new Button("Create Tournament");
            grid.add(createTournament, 3, 0);

            createTournament.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent e)
                {
                    try
                    {
                        LocalDate date = LocalDate.parse(dateTF.getText());
                        int prize = new Integer(prizeTF.getText());
                        String name = nameTF.getText();
                        if (name.equals(""))
                        {
                            System.err.println("no input");
                            return;
                        }
                        
                        nameTF.clear();
                        dateTF.clear();
                        prizeTF.clear();
                        
                        mediator.createTournament(name, date, prize);
                    }
                    catch (Exception ex)
                    {
                        System.err.println("You can`t do that");
                    }
                }
            });

            TextField emailTF = new TextField();
            nameTF.setPromptText("Tournament Name");
            grid.add(emailTF, 0, 1);

            TextField passwordTF = new TextField();
            dateTF.setPromptText("Tournament Date");
            grid.add(passwordTF, 1, 1);

            CheckBox isAdminCB = new CheckBox("Is Admin");
            grid.add(isAdminCB, 2, 1);

            Button createUser = new Button("Create User");
            grid.add(createUser, 3, 1);

            createUser.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent e)
                {
                    try
                    {
                        String email = emailTF.getText();
                        if (email.equals(""))
                        {
                            System.err.println("no input");
                            return;
                        }
                        String password = passwordTF.getText();
                        if (password.equals(""))
                        {
                            System.err.println("no input");
                            return;
                        }
                        boolean isAdmin = isAdminCB.isSelected();
                        
                        emailTF.clear();
                        passwordTF.clear();
                        isAdminCB.setSelected(false);
                        mediator.createUser(email, password, isAdmin);
                    }
                    catch (Exception ex)
                    {
                        System.err.println("You can`t do that");
                    }
                }
            });

            vb.getChildren().add(grid);

        }

        Scene scene = new Scene(vb, 700, 700);

        stage.setScene(scene);

        stage.show();
    }

}
