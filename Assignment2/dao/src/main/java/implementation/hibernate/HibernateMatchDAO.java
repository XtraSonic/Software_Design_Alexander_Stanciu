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
import ro.utcluj.alexanderstanciu.sd.dao.Entities.Match;
import ro.utcluj.alexanderstanciu.sd.dao.Interfaces.MatchGateway;

/**
 *
 * @author XtraSonic
 */
public class HibernateMatchDAO implements MatchGateway {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public HibernateMatchDAO(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public List<Match> findMatchesInGame(int gameId)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("from Match m where m.game.id= :id");
        query.setLong("id", gameId);
        List<Match> matchList = query.list();
        return matchList;
    }

    @Override
    public int insert(Match object)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();

        currentSession.persist(object);
        transaction.commit();

        return object.getId();
    }

    @Override
    public void update(Match object)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.update(object);
        transaction.commit();
    }

    @Override
    public void delete(Match object)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.delete(object);
        transaction.commit();
    }

    @Override
    public Match findById(int id)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Match result = (Match) currentSession.get(Match.class, id);
        transaction.commit();
        return result;
    }

    @Override
    public List<Match> findAll()
    {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Query query = currentSession.createQuery("from User");
        List<Match> matchList = query.list();
        transaction.commit();
        return matchList;
    }

}
