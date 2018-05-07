/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.webnews.server.Commands;

import ro.utcluj.alexanderstanciu.sd.webnews.model.writers.Writer;
import ro.utcluj.alexanderstanciu.sd.webnews.model.writers.WriterManager;

/**
 *
 * @author XtraSonic
 */
public class AddWriterCommand extends WriterManager implements Command{

    private String username;
    private String password;

    public AddWriterCommand(String username, String password)
    {
        this.username = username;
        this.password = password;
    }
    
    
    
    @Override
    public Object execute()
    {
        readWriters();
        Writer w = new Writer(username,password);
        if(isRegistered(w))
            return writers;
        writers.add(w);
        writeWriters();
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
        return "addedWriter";
    }
    
}
