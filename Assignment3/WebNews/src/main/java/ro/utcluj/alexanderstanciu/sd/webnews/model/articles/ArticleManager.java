/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.webnews.model.articles;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ro.utcluj.alexanderstanciu.sd.webnews.server.Commands.WriteArticleCommand;

/**
 *
 * @author XtraSonic
 */
public class ArticleManager {

    protected static final String ARTICLES_PATH = "articles.json";
    protected List<Article> articles;

    protected final Gson gson = new Gson();

    protected final void readArticles()
    {
        try (FileReader fr = new FileReader(ARTICLES_PATH))
        {
            articles = gson.fromJson(fr, new TypeToken<List<Article>>() {
                             }.getType());
        }

        catch (FileNotFoundException ex)
        {
            articles = new ArrayList<>();
        }
        catch (IOException ex)
        {
            Logger.getLogger(ArticleManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Article> getArticles()
    {
        return articles;
    }

    protected final void writeArticles()
    {
        try (FileWriter fw = new FileWriter(ARTICLES_PATH))
        {
            gson.toJson(articles, fw);
        }
        catch (IOException ex)
        {
            Logger.getLogger(ArticleManager.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}
