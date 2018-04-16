/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementation.hibernate;

import java.time.LocalDate;
import java.util.List;
import org.hibernate.SessionFactory;
import ro.utcluj.alexanderstanciu.sd.dao.DAOFactory;
import ro.utcluj.alexanderstanciu.sd.dao.Entities.Game;
import ro.utcluj.alexanderstanciu.sd.dao.Entities.Match;
import ro.utcluj.alexanderstanciu.sd.dao.Entities.Tournament;
import ro.utcluj.alexanderstanciu.sd.dao.Entities.User;
import ro.utcluj.alexanderstanciu.sd.dao.Interfaces.GameGateway;
import ro.utcluj.alexanderstanciu.sd.dao.Interfaces.MatchGateway;
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
        MatchGateway mg = f.getMatchGateway();
        GameGateway gg = f.getGameGateway();
        
        
        /*
        GameGateway mg = f.getGameGateway();
        Game m = new Game(0, player1, player2, 0, tournament, matches)*/

//TournamentGateway tg = f.getTournamentGateway();
       // Tournament t = new Tournament("First Paid Tournament", LocalDate.now().plusMonths(2), 100);
        //tg.insert(t);
        //System.out.println("Inserted ;)");
//        UserGateway ug = f.getUserGateway();
//        List<Tournament> lt = tg.findAll();
//        System.out.println("Hello");
        /*List<User> lu = ug.findAll();
        User a = ug.findByEmail("b");
        System.out.println(a.toString());
        Tournament t = tg.findById(1);
        System.out.println(t.toString());
        a.addTournament(t);
        System.out.println("\t\t" + a.getUserTournaments().toString() );
        System.out.println("\t\t" + t.getParticipants().toString() );
        tg.enrollUserInTournament(a,t);
        if(lt.get(0).getParticipants()==null)
            System.out.println("NOOOOO!!!!!!");
        else
            System.out.println(lt.get(0).getParticipants().toString());
        */
        
        f.closeConnection();
    }
}
