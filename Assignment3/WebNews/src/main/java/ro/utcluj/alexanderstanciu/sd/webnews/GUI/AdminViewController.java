/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.webnews.GUI;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import ro.utcluj.alexanderstanciu.sd.webnews.Message;
import ro.utcluj.alexanderstanciu.sd.webnews.client.Client;
import ro.utcluj.alexanderstanciu.sd.webnews.client.ServerListener;
import ro.utcluj.alexanderstanciu.sd.webnews.model.writers.Writer;

/**
 * FXML Controller class
 *
 * @author XtraSonic
 */
public class AdminViewController extends ViewController implements Observer {

    @FXML private TextField usernameTF;
    @FXML private TextField passwordTF;
    @FXML private ListView<Writer> writersListView;

    private Gson gson;
    private List<Writer> writers;
    private ObservableList writersData;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        gson = new Gson();
        ServerListener.getInstance().addObserver(this);
        Client.getInstance().requestWriters();
    }

    @FXML
    void createWriter(ActionEvent event)
    {
        if (usernameTF.getText().equals("")
            || passwordTF.getText().equals(""))
        {
            return;
        }
        Client.getInstance().requestAddWriter(usernameTF.getText(),
                                              passwordTF.getText());
    }

    @FXML
    void deleteWriter(ActionEvent event)
    {
        Writer w = writersListView.getSelectionModel().getSelectedItem();
        if (w == null)
        {
            return;
        }
        Client.getInstance().requestDeleteWriter(gson.toJson(w));
    }

    @FXML
    void updateWriter(ActionEvent event)
    {
        if (usernameTF.getText().equals("")
            || passwordTF.getText().equals(""))
        {
            return;
        }
        Writer w = writersListView.getSelectionModel().getSelectedItem();
        if (w == null)
        {
            return;
        }
        Client.getInstance().requestUpdateWriter(usernameTF.getText(),
                                                 passwordTF.getText(),
                                                 gson.toJson(w));
    }

    @FXML
    void viewArticles(ActionEvent event)
    {
        ServerListener sl = ServerListener.getInstance();
        sl.deleteObserver(this);
        changeScene(usernameTF, "/fxml/ArticlesView.fxml");
    }

    @Override
    public void update(Observable o, Object o1)
    {
        Message message = (Message) o1;
        switch (message.getCommand())
        {
            case "writersList":
                updateList((String) message.getArgs());
                break;
            case "deletedWriter":
                System.out.println("Deleted writer");
                System.out.println();
                updateList((String) message.getArgs());
                break;
            case "addedWriter":
                System.out.println("Added writer");
                System.out.println();
                updateList((String) message.getArgs());
                break;
            case "updatedWriters":
                System.out.println("Updated writer");
                System.out.println();
                updateList((String) message.getArgs());
                break;
            default:
                System.out.println("Command " + message.getCommand() + " ignored");
        }
    }

    private void updateList(String json)
    {
        writers = gson.fromJson(json, new TypeToken<List<Writer>>() {
                        }.getType());

        Platform.runLater(() ->
        {

            writersData = FXCollections.observableArrayList();
            writers.stream().forEach(a -> writersData.add(a));
            writersListView.setItems(writersData);
        });
    }
}
