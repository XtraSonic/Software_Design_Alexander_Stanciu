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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ro.utcluj.alexanderstanciu.sd.webnews.model.articles.Article;
import ro.utcluj.alexanderstanciu.sd.webnews.Message;
import ro.utcluj.alexanderstanciu.sd.webnews.client.Client;
import ro.utcluj.alexanderstanciu.sd.webnews.client.ServerListener;

/**
 * FXML Controller class
 *
 * @author XtraSonic
 */
public class ArticlesViewController extends ViewController implements Observer {

    @FXML private TextField usernameTF;
    @FXML private TextField passwordTF;

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
            case "WriterLogin":
                System.out.println("Loging in as a writer");
                client.setUsername((String) message.getArgs());
                ServerListener.getInstance().deleteObserver(this);
                changeScene(usernameTF, "/fxml/WriterView.fxml");
                break;
            case "FailedLogin":
                System.out.println("Failed to log in");
                break;
            case "AdminLogin":
                System.out.println("Loging in as an admin");
                client.setUsername((String) message.getArgs());
                ServerListener.getInstance().deleteObserver(this);
                changeScene(usernameTF, "/fxml/AdminView.fxml");
                break;
            case "UpdateAvailable":
                System.out.println("Update Available: " + message.getArgs());
                client.respond(message.getArgs());
                break;
            default:
                System.out.println("Command " + message.getCommand() + " ignored");
        }
    }

    @FXML public void login(ActionEvent event) throws IOException
    {
        System.out.println("Logging in ...");
        if (usernameTF.getText().equals("") || passwordTF.getText().equals(""))
        {
            return;
        }
        client.requestLogin(usernameTF.getText(), passwordTF.getText());
    }

    @FXML public void readArticle(ActionEvent event) throws IOException
    {
        System.out.println("Reading Article...");
        ServerListener.getInstance().deleteObserver(this);
        changeScene(usernameTF,
                    "/fxml/ArticleView.fxml",
                    articles.get(articleListView
                            .getSelectionModel()
                            .getSelectedIndex()));

    }

    private void changeScene(Node source, String fxmlPath, Article article)
    {

        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent parent = loader.load();
            Scene view = new Scene(parent);
            Stage window = (Stage) (source).getScene().getWindow();
            Platform.runLater(() ->
            {
                ArticleViewController controller = loader.<ArticleViewController>getController();
                controller.setCurrentArticle(article);
                window.setScene(view);
                window.show();

            });

        }
        catch (IOException ex)
        {
            Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
