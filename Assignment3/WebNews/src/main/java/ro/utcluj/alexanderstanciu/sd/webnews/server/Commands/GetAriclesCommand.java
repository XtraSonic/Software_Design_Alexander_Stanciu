/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.webnews.server.Commands;

import ro.utcluj.alexanderstanciu.sd.webnews.model.articles.ArticleManager;

/**
 *
 * @author XtraSonic
 */
public class GetAriclesCommand extends ArticleManager implements Command{

    @Override
    public Object execute()
    {
        readArticles();
        return getSerializedArticles();
    }

    @Override
    public String generatesGlobalUpdates()
    {
        return "";
    }

    @Override
    public String getResponse()
    {
        return "articlelList";
    }

    private Object getSerializedArticles()
    {
        return gson.toJson(articles);
    }

}
