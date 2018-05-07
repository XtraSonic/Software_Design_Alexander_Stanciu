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
public class DeleteWriterCommand extends WriterManager implements Command{
    private Writer writer;

    public DeleteWriterCommand(String writer)
    {
        this.writer = gson.fromJson(writer,Writer.class);
    }

    @Override
    public Object execute()
    {
        readWriters();
        deleteWriter(writer);
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
        return "deletedWriter";
    }
    
}
