/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.webnews.GUI.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
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
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import ro.utcluj.alexanderstanciu.sd.webnews.Message;
import ro.utcluj.alexanderstanciu.sd.webnews.client.Client;
import ro.utcluj.alexanderstanciu.sd.webnews.client.ServerListener;
import ro.utcluj.alexanderstanciu.sd.webnews.model.articles.Article;

/**
 * FXML Controller class
 *
 * @author XtraSonic
 */
public class WriterViewController extends ViewController implements Observer {

    @FXML private TextField titleTF;
    @FXML private TextField imageURLTF;
    @FXML private TextArea abstractTA;
    @FXML private TextArea bodyTA;

    @FXML private ListView<String> articleListView;

    private List<Article> articles;
    private ObservableList articleData;
    private Client client;
    private Gson gson;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        gson = new Gson();
        ServerListener sl = ServerListener.getInstance();
        sl.addObserver(this);
        client = Client.getInstance();

        client.requestArticles();
        abstractTA.setWrapText(true);
        bodyTA.setWrapText(true);
        articleListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }

    @FXML public void submit(ActionEvent event) throws IOException
    {
        if (titleTF.getText().equals("")
            || abstractTA.getText().equals("")
            || bodyTA.getText().equals(""))
        {
            System.out.println("There are empty fields");
            return;
        }

        System.out.println("Submitting ...");

        
        String relatedArticles = gson.toJson(articleListView.getSelectionModel().getSelectedIndices());
        client.requestWriteArticle(titleTF.getText(),
                                   abstractTA.getText(),
                                   bodyTA.getText(),
                                   imageURLTF.getText(),
                                   relatedArticles
        );

    }

   @FXML
    void viewArticles(ActionEvent event)
    {
        ServerListener sl = ServerListener.getInstance();
        sl.deleteObserver(this);
        changeScene(bodyTA, "/fxml/ArticlesView.fxml");
    }

    @Override
    public void update(Observable o, Object o1)
    {
        Message message = (Message) o1;
        switch (message.getCommand())
        {
            case "articlelList":
                String json = (String) message.getArgs();
                articles = gson.fromJson(json, new TypeToken<List<Article>>() {
                                 }.getType());
                Platform.runLater(() ->
                {
                    articleListView.setCellFactory((final ListView<String> list) -> new ListCell<String>() {
                        {
                            Text text = new Text();
                            text.setId("fancytext");
                            text.wrappingWidthProperty().bind(list.widthProperty().subtract(15));
                            text.textProperty().bind(itemProperty());

                            setPrefWidth(0);
                            setGraphic(text);
                        }
                    });
                    articleData = FXCollections.observableArrayList();
                    articles.stream().forEach(a -> articleData.add(a.getDescription()));
                    articleListView.setItems(articleData);
                });
                break;
            case "UpdateAvailable":
                System.out.println("Update Available: " + message.getArgs());
                client.respond(message.getArgs());
                break;
            default:
                System.out.println("Command " + message.getCommand() + " ignored");
        }
    }
}
