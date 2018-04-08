/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.business;

import implementation.jdbc.JDBCTournamentDAO;
import implementation.jdbc.JDBCUserDAO;

/**
 *
 * @author XtraSonic
 */
public class SessionBuilder {
    public static UserSession getUserSession()
    {
        return new UserSession(new JDBCUserDAO());
    }
    
    public static TournamentSession getTournamentSession()
    {
        return new TournamentSession(new JDBCTournamentDAO());
    }
}
