/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.webnews.server.Commands;

import com.google.gson.reflect.TypeToken;
import java.util.List;
import ro.utcluj.alexanderstanciu.sd.webnews.model.articles.Article;
import ro.utcluj.alexanderstanciu.sd.webnews.model.articles.ArticleManager;

/**
 *
 * @author XtraSonic
 */
public class WriteArticleCommand extends ArticleManager implements Command {

    private String title;
    private String abstr;
    private String author;
    private String body;
    private String imageURL;
    private List<Integer> relatedArticles;

    public WriteArticleCommand(String title, String author, String abstr, String body, String imageURL,String relatedArticles)
    {
        this.title = title;
        this.abstr = abstr;
        this.author = author;
        this.body = body;
        this.imageURL = imageURL;
        this.relatedArticles = gson.fromJson(relatedArticles, new TypeToken<List<Integer>>() {
                                     }.getType());

        //possible improvements: don`t read all the articlaes that exist already, just add the new one 
        readArticles();
    }

    @Override
    public Object execute()
    {
        Article newArticle = new Article(title, author, abstr, body, imageURL, relatedArticles);
        articles.add(newArticle);
        writeArticles();
        return null;
    }

    @Override
    public String generatesGlobalUpdates()
    {
        return "getArticles";
    }

    @Override
    public String getResponse()
    {
        return "";
    }

}
