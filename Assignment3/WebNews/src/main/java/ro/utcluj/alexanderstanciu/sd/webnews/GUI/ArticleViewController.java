/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.webnews.GUI;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.stage.Stage;
import ro.utcluj.alexanderstanciu.sd.webnews.Message;
import ro.utcluj.alexanderstanciu.sd.webnews.client.Client;
import ro.utcluj.alexanderstanciu.sd.webnews.client.ServerListener;
import ro.utcluj.alexanderstanciu.sd.webnews.model.articles.Article;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author XtraSonic
 */
public class ArticleViewController extends ViewController implements Observer {
    
    @FXML private TextField titleTF;
    @FXML private TextField authorTF;
    @FXML private ImageView pictureIV;
    @FXML private TextArea abstractTA;
    @FXML private TextArea bodyTA;
    @FXML private ListView<String> articleListView;
    
    private List<Article> articles;
    private List<Article> relatedArticles;
    private ObservableList articleData;
    private Client client;
    private Gson gson;
    private Article currentArticle;

    /**
     * Initializes the controller class.
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
    
    public void setCurrentArticle(Article article)
    {
        this.currentArticle = article;
        titleTF.setText(currentArticle.getTitle());
        authorTF.setText(currentArticle.getAuthor());
        abstractTA.setText(currentArticle.getAbstr());
        bodyTA.setText(currentArticle.getBody());
        
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
            if (currentArticle.getRelatedArticles() != null)
            {
                relatedArticles = new ArrayList<>();
                currentArticle.getRelatedArticles().stream().forEach(index
                        ->
                {
                    relatedArticles.add(articles.get(index));
                    articleData.add(articles.get(index).getDescription());
                });
                articleListView.setItems(articleData);
            }
            else
            {
                articleListView.getItems().clear();
            }
            if (article.getTitle().equals("Works"))
            {
                pictureIV.setImage(null);
                return;
                
            }
            if (currentArticle.getImageURL() == null || 
                currentArticle.getImageURL().equals(""))
            {
                pictureIV.setImage(null);
                return;
            }
            Image image = new Image(currentArticle.getImageURL());
            pictureIV.setImage(image);
            
        });
        
    }
    
    @FXML
    void goToArticle(ActionEvent event)
    {
        setCurrentArticle(relatedArticles.get(
                articleListView.getSelectionModel().getSelectedIndex()
        ));
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
                
                break;
            default:
                System.out.println("Command " + message.getCommand() + " ignored");
        }
    }
    
}
