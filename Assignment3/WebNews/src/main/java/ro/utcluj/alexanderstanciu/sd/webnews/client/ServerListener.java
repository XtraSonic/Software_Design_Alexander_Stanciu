/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.webnews.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import ro.utcluj.alexanderstanciu.sd.webnews.Message;

/**
 *
 * @author XtraSonic
 */
public class ServerListener extends Observable implements Runnable {

    private ObjectInputStream input;
    private static ServerListener SINGLETON;
    private boolean opened;

    private ServerListener()
    {
        input = Client.getInstance().getInput();
        opened = true;
    }

    public static ServerListener getInstance()
    {
        if (SINGLETON == null)
        {
            SINGLETON = new ServerListener();
        }
        return SINGLETON;
    }

    @Override
    public void run()
    {
        try
        {
            while (opened)
            {
                Message message = (Message) input.readObject();

                System.out.println("Recieved message from server: " + message);
                System.out.println();
                setChanged();
                notifyObservers(message);
            }
        }
        catch (IOException ex)
        {
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(ServerListener.class.getName())
                    .log(Level.SEVERE, null, ex);

        }
        finally
        {
            close();
        }
    }

    void close()
    {
        opened = false;
        try
        {
            input.close();
        }
        catch (IOException ex)
        {
            SINGLETON = null;
            Logger.getLogger(ServerListener.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

    }

}
