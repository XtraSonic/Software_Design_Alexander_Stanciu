/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.business;

import ro.utcluj.alexanderstanciu.sd.dao.TournamentDAO;
import ro.utcluj.alexanderstanciu.sd.dao.UserDAO;

/**
 *
 * @author XtraSonic
 */
public class SessionBuilder {
    public static UserSession getUserSession()
    {
        return new UserSession(new UserDAO());
    }
    
    public static TournamentSession getTournamentSession()
    {
        return new TournamentSession(new TournamentDAO());
    }
}
