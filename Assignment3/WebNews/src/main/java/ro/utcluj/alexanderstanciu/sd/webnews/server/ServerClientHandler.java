/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.webnews.server;

import ro.utcluj.alexanderstanciu.sd.webnews.Message;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import ro.utcluj.alexanderstanciu.sd.webnews.server.Commands.Command;
import ro.utcluj.alexanderstanciu.sd.webnews.server.Commands.ServerCommandFactory;

/**
 *
 * @author XtraSonic
 */
public class ServerClientHandler extends Observable implements Runnable {

    private Socket clientSocket;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private final int id;
    

    
    
    public ServerClientHandler(Socket client, int id) throws IOException
    {
        this.id = id;
        this.clientSocket = client;
        output = new ObjectOutputStream(clientSocket.getOutputStream());
        input = new ObjectInputStream(clientSocket.getInputStream());
    }

    @Override
    public void run()
    {

        while (!clientSocket.isClosed())
        {
            try
            {
                String received = (String) input.readObject();
                System.out.println("Recieved message from client " + id + 
                                   "\nMessage:"+received);
                String[] args = received.split("\t");
                Command command = ServerCommandFactory.getCommand(args);
                if (command != null)
                {
                    String updates = command.generatesGlobalUpdates();
                    if (updates.equals(""))
                    {
                        Object result = command.execute();
                        String responseCommand = command.getResponse();
                        send(responseCommand,result);
                        
                    }
                    else
                    {
                        command.execute();
                        List<Object> updateArgs =new ArrayList<>();
                        updateArgs.add("");
                        updateArgs.add(updates);
                        setChanged();
                        notifyObservers(updateArgs);
                    }
                }
                else
                {
                    System.out.println("Command was not understood");
                }
            }
            catch (IOException | ClassNotFoundException ex)
            {
                try
                {
                    clientSocket.close();
                }
                catch (IOException ex1)
                {
                    Logger.getLogger(ServerClientHandler.class.getName()).log(Level.SEVERE, null, ex1);
                    return;
                }
            }
        }
        System.out.println("Client " + id + " no longer connected");
        setChanged();
        List<Object> args = new ArrayList<>();
        args.add("ClosedConnection");
        notifyObservers(args);

    }

    public void notifyClient(String message)
    {
       send("UpdateAvailable",message); 
    }

    private void send(String responseCommand, Object result)
    {
        if(responseCommand.equals(""))
            return;
        try
        {
            Message message = new Message(responseCommand, result);
            System.out.println("Updating client "+id +" with "+ message);
            System.out.println();
            output.writeObject(message);
        }
        catch (IOException ex)
        {
            Logger.getLogger(ServerClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
