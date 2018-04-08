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
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ro.utcluj.alexanderstanciu.sd.dao.Entities.Tournament;
import ro.utcluj.alexanderstanciu.sd.dao.Interfaces.TournamentGateway;

/**
 *
 * @author XtraSonic
 */
public class HibernateTournamentDAO implements TournamentGateway {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public HibernateTournamentDAO()
    {
        sessionFactory = HibernateUtil.getSessionFactory();
    }
    
    @Override
    public int insert(Tournament object)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();

        currentSession.persist(object);
        transaction.commit();

        return object.getId();
    }

    @Override
    public void update(Tournament object)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.update(object);
        transaction.commit();
    }

    @Override
    public void delete(Tournament object)
    {

        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.delete(object);
        transaction.commit();
    }

    @Override
    public Tournament findById(int id)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Tournament result = (Tournament) currentSession.get(Tournament.class, id);
        transaction.commit();
        return result;
    }

    @Override
    public List<Tournament> findAll()
    {

        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Query query = currentSession.createQuery("from Tournament");
        List<Tournament> tournamentList = query.list();
        transaction.commit();
        return tournamentList;
    }

    @Override
    public void closeConnection()
    {
        sessionFactory.close();
        StandardServiceRegistryBuilder.destroy(sessionFactory.getSessionFactoryOptions().getServiceRegistry());
    }
}
