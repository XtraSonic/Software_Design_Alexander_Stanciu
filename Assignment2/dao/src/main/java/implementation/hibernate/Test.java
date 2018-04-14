/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementation.hibernate;

import java.util.List;
import org.hibernate.SessionFactory;
import ro.utcluj.alexanderstanciu.sd.dao.Entities.Tournament;
import ro.utcluj.alexanderstanciu.sd.dao.Entities.User;

/**
 *
 * @author XtraSonic
 */
public class Test {
    /*public static void main(String[] args)
    {
        HibernateTournamentDAO h = new HibernateTournamentDAO();
        System.out.println("got here ");
        List<Tournament> t = h.findAll();
        System.out.println("got here 2");
        t.forEach((e) ->
        {
            System.out.println(e.toString());
        });
        
        
        h.closeConnection();
        
        HibernateUserDAO q = new HibernateUserDAO();
        System.out.println("got here ");
        List<User> ul =  q.findAll();
        System.out.println("got here 2");
        ul.forEach((e) ->
        {
            System.out.println(e.toString());
        });
        
        q.closeConnection();
        
    }*/
    public static void main(String[] args)
    {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        System.out.println("hello");
        sf.close();
    }
}
