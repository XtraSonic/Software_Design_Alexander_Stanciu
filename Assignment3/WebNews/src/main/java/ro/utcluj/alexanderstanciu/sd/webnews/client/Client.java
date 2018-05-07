/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.webnews.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import ro.utcluj.alexanderstanciu.sd.webnews.server.Server;

/**
 *
 * @author XtraSonic
 */
public class Client {

    private static Client SINGLETON;

    private Socket socketClient;
    private static final int PORT = Server.PORT;
    private static final String ADDRESS = "localhost";
    private ObjectOutputStream output;
    private ObjectInputStream input;
    
    private String username;

    private Client()
    {
    }

    public static Client getInstance()
    {
        if (SINGLETON == null)
        {
            SINGLETON = new Client();
        }
        return SINGLETON;
    }

    public void connect()
    {
        try
        {
            socketClient = new Socket(ADDRESS, PORT);
            System.out.println("Client connected");
            output = new ObjectOutputStream(socketClient.getOutputStream());
            input = new ObjectInputStream(socketClient.getInputStream());
            ServerListener sl = ServerListener.getInstance();
            Thread t = new Thread(sl);
            t.start();
        }
        catch (IOException ex)
        {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObjectOutputStream getOutput()
    {
        return output;
    }

    public ObjectInputStream getInput()
    {
        return input;
    }

    public void requestArticles()
    {
        try
        {
            output.writeObject("getArticles");
        }
        catch (IOException ex)
        {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void requestLogin(String username, String password)
    {
        try
        {
            output.writeObject("login\t"+username+'\t'+password);
        }
        catch (IOException ex)
        {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void close()
    {
        try
        {
            ServerListener.getInstance().close();
            input.close();
            output.close();
            socketClient.close();
        }
        catch (IOException ex)
        {
            SINGLETON = null;
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void requestWriteArticle(String title, String abstr, String body, String imageURL, String relatedArticles)
    {
        try
        {
            output.writeObject("writeArticle\t"+
                               title+'\t'+
                               username+'\t'+
                               abstr+'\t'+
                               body+'\t'+
                               imageURL+'\t'+
                               relatedArticles);
        }
        catch (IOException ex)
        {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void respond(Object args)
    {
        String arg = (String)args;
        switch (arg){
            case "getArticles":
                requestArticles();
                break;
            default: System.out.println("Confused, what shoud i respond to " 
                                        +arg + " ?\n");
                        }
    }

    public void requestAddWriter(String username, String password)
    {
        try
        {
            output.writeObject("addWriter\t"+
                               username+'\t'+
                               password+'\t');
        }
        catch (IOException ex)
        {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void requestWriters()
    {
        try
        {
            output.writeObject("getWriters");
        }
        catch (IOException ex)
        {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void requestDeleteWriter(String writer)
    {
        try
        {
            output.writeObject("deleteWriter\t"+
                               writer);
        }
        catch (IOException ex)
        {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void requestUpdateWriter(String username, String password, String writer)
    {
        try
        {
            output.writeObject("updateWriter\t"+
                               username+'\t'+
                               password+'\t'+
                               writer);
        }
        catch (IOException ex)
        {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}

