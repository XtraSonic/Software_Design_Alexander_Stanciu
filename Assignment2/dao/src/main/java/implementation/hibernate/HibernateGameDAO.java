/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementation.hibernate;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ro.utcluj.alexanderstanciu.sd.dao.Entities.Game;
import ro.utcluj.alexanderstanciu.sd.dao.Interfaces.GameGateway;

/**
 *
 * @author XtraSonic
 */
public class HibernateGameDAO implements GameGateway {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public HibernateGameDAO()
    {
        sessionFactory = HibernateUtil.getSessionFactory();
    }
    
    @Override
    public List<Game> getGamesInTournament(int id)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("from Game g where g.tournament.id= :id");
        query.setLong("id", id);
        List<Game> gameList = query.list();
        return gameList;

    }

    @Override
    public int insert(Game game)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();

        currentSession.persist(game);
        transaction.commit();

        return game.getId();
    }

    @Override
    public void update(Game game)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.update(game);
        transaction.commit();
    }

    @Override
    public void delete(Game game)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.delete(game);
        transaction.commit();
    }

    @Override
    public Game findById(int id)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Game result = (Game) currentSession.get(Game.class, id);
        transaction.commit();
        return result;
    }

    @Override
    public List<Game> findAll()
    {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Query query = currentSession.createQuery("from Game");
        List<Game> gameList = query.list();
        transaction.commit();
        return gameList;
    }

    @Override
    public void closeConnection()
    {
        sessionFactory.close();
    }
}
