/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.webnews.server.Commands;

/**
 *
 * @author XtraSonic
 */
public interface Command {
    public Object execute();
    public String generatesGlobalUpdates();
    public String getResponse();
}
