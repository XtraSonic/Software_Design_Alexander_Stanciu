/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.webnews;

import java.io.Serializable;

/**
 *
 * @author XtraSonic
 */
public class Message implements Serializable{
    private final String command;
    private final Object args;

    public Message(String command, Object args)
    {
        this.command = command;
        this.args = args;
    }

    @Override
    public String toString()
    {
        return "Message{" + "command=" + command + ", args=" + args + '}';
    }

    public String getCommand()
    {
        return command;
    }

    public Object getArgs()
    {
        return args;
    }
    
}
