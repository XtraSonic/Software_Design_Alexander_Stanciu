/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementation.hibernate;

import ro.utcluj.alexanderstanciu.sd.dao.DAOFactory;
import ro.utcluj.alexanderstanciu.sd.dao.Interfaces.GameGateway;
import ro.utcluj.alexanderstanciu.sd.dao.Interfaces.MatchGateway;
import ro.utcluj.alexanderstanciu.sd.dao.Interfaces.TournamentGateway;
import ro.utcluj.alexanderstanciu.sd.dao.Interfaces.UserGateway;

/**
 *
 * @author XtraSonic
 */
public class HibernateGatewayFactory extends DAOFactory{

    @Override
    public GameGateway getGameGateway()
    {
        return new HibernateGameDAO();
    }

    @Override
    public UserGateway getUserGateway()
    {
        return new HibernateUserDAO();
    }

    @Override
    public TournamentGateway getTournamentGateway()
    {
        return new HibernateTournamentDAO();
    }

    @Override
    public MatchGateway getMatchGateway()
    {
        return new HibernateMatchDAO();
    }
    
}
