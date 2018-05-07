/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.webnews.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author XtraSonic
 */
public class ServerMain extends Thread implements Observer {

    public static final int PORT = 4315;

    private static ServerMain SINGELETON;
    private final ServerSocket serverSocket;
    private int clientID;

    private List<ServerClientHandler> clients;

    private ServerMain() throws IOException
    {
        clientID = 1;
        clients = new LinkedList<>();
        serverSocket = new ServerSocket(PORT);
    }

    public static ServerMain getInstance() throws IOException
    {
        if (SINGELETON == null)
        {
            SINGELETON = new ServerMain();
        }
        return SINGELETON;
    }

    @Override
    public void run()
    {
        Socket clientSocket;
        System.out.println("Server started listening");
        while (!serverSocket.isClosed())
        {
            System.out.println("Server listening...");
            try
            {
                clientSocket = serverSocket.accept();
                System.out.println("New connection from " + clientSocket.getInetAddress());
                ServerClientHandler handler = new ServerClientHandler(clientSocket, clientID++);
                clients.add(handler);
                handler.addObserver(this);
                Thread thread = new Thread(handler);
                thread.start();
                System.out.println("All connections: " + clients.toString());
                System.out.println();
            }
            catch (IOException ex)
            {
                Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void close()
    {
        try
        {
            serverSocket.close();
        }
        catch (IOException ex)
        {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Observable o, Object o1)
    {
        List<Object> args = (List<Object>) o1;
        String command = (String)args.get(0);
        if(command.equals("ClosedConnection"))
        {
            clients.remove(o);
            return;
        }
        clients.stream().forEach(e -> e.notifyClient((String) args.get(1)));
    }

    public static void main(String[] args)
    {
        ServerMain s;
        try
        {
            s = ServerMain.getInstance();
            s.start();
        }
        catch (IOException ex)
        {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
