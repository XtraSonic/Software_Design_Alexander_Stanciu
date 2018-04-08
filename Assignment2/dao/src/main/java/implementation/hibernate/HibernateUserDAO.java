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
import ro.utcluj.alexanderstanciu.sd.dao.Entities.User;
import ro.utcluj.alexanderstanciu.sd.dao.Interfaces.UserGateway;

/**
 *
 * @author XtraSonic
 */
public class HibernateUserDAO implements UserGateway {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public HibernateUserDAO()
    {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public User findByEmail(String email)
    {

        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("from User u where u.email = " + email);
        User user = (User) query.uniqueResult();
        return user;
    }

    @Override
    public int insert(User object)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();

        currentSession.persist(object);
        transaction.commit();

        return object.getId();
    }

    @Override
    public void update(User object)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.update(object);
        transaction.commit();
    }

    @Override
    public void delete(User object)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.delete(object);
        transaction.commit();
    }

    @Override
    public User findById(int id)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        User result = (User) currentSession.get(User.class, id);
        transaction.commit();
        return result;

    }

    @Override
    public List<User> findAll()
    {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Query query = currentSession.createQuery("from User");
        List<User> userList = query.list();
        transaction.commit();
        return userList;
    }

    @Override
    public void closeConnection()
    {
        sessionFactory.close();
        StandardServiceRegistryBuilder.destroy(sessionFactory.getSessionFactoryOptions().getServiceRegistry());
    }
}
