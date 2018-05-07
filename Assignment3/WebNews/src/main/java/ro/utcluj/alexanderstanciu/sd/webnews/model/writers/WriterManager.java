/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.webnews.model.writers;

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
import java.util.stream.Collectors;
import ro.utcluj.alexanderstanciu.sd.webnews.model.articles.Article;

/**
 *
 * @author XtraSonic
 */
public class WriterManager {

    protected static final String WRITERS_PATH = "writers.json";
    protected List<Writer> writers;

    protected final Gson gson = new Gson();

    protected final void readWriters()
    {
        try (FileReader fr = new FileReader(WRITERS_PATH))
        {
            writers = gson.fromJson(fr, new TypeToken<List<Writer>>() {
                             }.getType());
        }

        catch (FileNotFoundException ex)
        {
            writers = new ArrayList<>();
        }
        catch (IOException ex)
        {
            Logger.getLogger(WriterManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Writer> getWriters()
    {
        return writers;
    }

    protected final void writeWriters()
    {
        try (FileWriter fw = new FileWriter(WRITERS_PATH))
        {
            gson.toJson(writers, fw);
        }
        catch (IOException ex)
        {
            Logger.getLogger(WriterManager.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
    protected final boolean isRegistered(Writer writer)
    {
        return writers.stream().anyMatch(w-> {
            return writer.equals(w);
        });
    }
    
    protected final void deleteWriter(Writer writer)
    {
        writers = writers.stream().filter(w->{
            return !writer.equals(w);
                }).collect(Collectors.toList());
    }
    
    protected final void updateWriter(Writer writer, Writer newWriter)
    {
        writers.stream().forEach(w->{
            if(w.equals(writer))
                w.update(newWriter);
        });
    }
}


