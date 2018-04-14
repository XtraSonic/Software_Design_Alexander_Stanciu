/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementation.jdbc;

import ro.utcluj.alexanderstanciu.sd.dao.DAOFactory;
import ro.utcluj.alexanderstanciu.sd.dao.Interfaces.GameGateway;
import ro.utcluj.alexanderstanciu.sd.dao.Interfaces.MatchGateway;
import ro.utcluj.alexanderstanciu.sd.dao.Interfaces.TournamentGateway;
import ro.utcluj.alexanderstanciu.sd.dao.Interfaces.UserGateway;

/**
 *
 * @author XtraSonic
 */
public class JdbcGatewayFactory extends DAOFactory {

    @Override
    public GameGateway getGameGateway()
    {
        return new JDBCGameDAO();
    }

    @Override
    public UserGateway getUserGateway()
    {
        return new JDBCUserDAO();
    }

    @Override
    public TournamentGateway getTournamentGateway()
    {
        return new JDBCTournamentDAO();
    }

    @Override
    public MatchGateway getMatchGateway()
    {
        return new JDBCMatchDAO();
    }

    @Override
    public void closeConnection()
    {
    }

}
