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
public class LoginCommand extends WriterManager implements Command{
    
    
    private String username;
    private String password;
    private String command;

    public LoginCommand(String username, String password)
    {
        this.username = username;
        this.password = password;
        command = "FailedLogin";
    }
    

    @Override
    public Object execute()
    {
        
        if(username.equals("aa") && password.equals("aa"))
        {
            command = "AdminLogin";
            return username;
        }
            
        readWriters();
        Writer writer = new Writer(username, password);
        if(isRegistered(writer))
        {
            command = "WriterLogin";
            return username;
        }
        return null;
    }

    @Override
    public String generatesGlobalUpdates()
    {
        return "";
    }

    @Override
    public String getResponse()
    {
        return command;
    }
    
}
