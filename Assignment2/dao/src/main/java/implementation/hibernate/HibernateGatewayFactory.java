/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementation.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ro.utcluj.alexanderstanciu.sd.dao.DAOFactory;
import ro.utcluj.alexanderstanciu.sd.dao.Interfaces.GameGateway;
import ro.utcluj.alexanderstanciu.sd.dao.Interfaces.MatchGateway;
import ro.utcluj.alexanderstanciu.sd.dao.Interfaces.TournamentGateway;
import ro.utcluj.alexanderstanciu.sd.dao.Interfaces.UserGateway;

/**
 *
 * @author XtraSonic
 */
public class HibernateGatewayFactory extends DAOFactory {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public GameGateway getGameGateway()
    {
        return new HibernateGameDAO(sessionFactory);
    }

    @Override
    public UserGateway getUserGateway()
    {
        return new HibernateUserDAO(sessionFactory);
    }

    @Override
    public TournamentGateway getTournamentGateway()
    {
        return new HibernateTournamentDAO(sessionFactory);
    }

    @Override
    public MatchGateway getMatchGateway()
    {
        return new HibernateMatchDAO(sessionFactory);
    }

    @Override
    public void closeConnection()
    {

        sessionFactory.close();
        StandardServiceRegistryBuilder.destroy(sessionFactory.getSessionFactoryOptions().getServiceRegistry());
    }

}
