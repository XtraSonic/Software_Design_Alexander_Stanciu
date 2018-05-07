/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.webnews.server.Commands;

import ro.utcluj.alexanderstanciu.sd.webnews.model.writers.WriterManager;

/**
 *
 * @author XtraSonic
 */
public class GetWritersCommand extends WriterManager implements Command{

    @Override
    public Object execute()
    {
        readWriters();
        return gson.toJson(writers);
    }

    @Override
    public String generatesGlobalUpdates()
    {
        return "";
    }

    @Override
    public String getResponse()
    {
        return "writersList";
    }
    
}
