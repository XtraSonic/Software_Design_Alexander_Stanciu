/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementation.hibernate;

import java.util.List;
import org.hibernate.SessionFactory;
import ro.utcluj.alexanderstanciu.sd.dao.DAOFactory;
import ro.utcluj.alexanderstanciu.sd.dao.Entities.Tournament;
import ro.utcluj.alexanderstanciu.sd.dao.Entities.User;
import ro.utcluj.alexanderstanciu.sd.dao.Interfaces.TournamentGateway;
import ro.utcluj.alexanderstanciu.sd.dao.Interfaces.UserGateway;

/**
 *
 * @author XtraSonic
 */
public class Test {
   
    public static void main(String[] args)
    {
        DAOFactory f =  DAOFactory.getInstance();
        TournamentGateway tg = f.getTournamentGateway();
        UserGateway ug = f.getUserGateway();
        List<Tournament> lt = tg.findAll();
        /*List<User> lu = ug.findAll();
        User a = ug.findByEmail("b");
        System.out.println(a.toString());
        Tournament t = tg.findById(1);
        System.out.println(t.toString());
        a.addTournament(t);
        System.out.println("\t\t" + a.getUserTournaments().toString() );
        System.out.println("\t\t" + t.getParticipants().toString() );
        tg.enrollUserInTournament(a,t);
        */if(lt.get(0).getParticipants()==null)
            System.out.println("NOOOOO!!!!!!");
        else
            System.out.println(lt.get(0).getParticipants().toString());
        
        f.closeConnection();
    }
}
