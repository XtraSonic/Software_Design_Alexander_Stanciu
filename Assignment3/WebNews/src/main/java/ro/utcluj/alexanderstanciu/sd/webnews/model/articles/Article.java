/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.webnews.model.articles;

import java.util.List;

/**
 *
 * @author XtraSonic
 */
public class Article {
    private String title;
    private String abstr;
    private String author;
    private String body;
    private String imageURL;
    private List<Integer> relatedArticles;

    
    public Article(String title,  String author, String abstr, String body, String imageURL,List<Integer> relatedArticles)
    {
        this.title = title;
        this.author = author;
        this.abstr = abstr;
        this.imageURL = imageURL;
        this.body = body;
        this.relatedArticles = relatedArticles;
    }

    @Override
    public String toString()
    {
        return "Article{" + "title=" + title + ", abstr=" + abstr + ", author=" + author + ", body=" + body + ", relatedArticles=" + relatedArticles + '}';
    }

    public String getTitle()
    {
        return title;
    }

    public String getAbstr()
    {
        return abstr;
    }

    public String getAuthor()
    {
        return author;
    }

    public String getBody()
    {
        return body;
    }

    public List<Integer> getRelatedArticles()
    {
        return relatedArticles;
    }

    public String getImageURL()
    {
        return imageURL;
    }

    public void setImageURL(String imageURL)
    {
        this.imageURL = imageURL;
    }
    
    
    
    
    public String getDescription()
    {
        return title + " by " + author + "\n\t"+ abstr;
    }
    
    
}
