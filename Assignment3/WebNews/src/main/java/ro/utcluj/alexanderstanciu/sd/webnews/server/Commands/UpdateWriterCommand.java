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
public class UpdateWriterCommand extends WriterManager implements Command{
    
    
    private String username;
    private String password;
    private Writer writer;

    public UpdateWriterCommand(String username, String password, String writer)
    {
        this.username = username;
        this.password = password;
        this.writer = gson.fromJson(writer,Writer.class);
    }

    @Override
    public Object execute()
    {
        readWriters();
        updateWriter(writer, new Writer(username, password));
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
        return "updatedWriters";
    }
    
    
}
