/*
 * 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import ro.utcluj.alexanderstanciu.sd.business.Entities.Game;
import ro.utcluj.alexanderstanciu.sd.business.Entities.Match;
import ro.utcluj.alexanderstanciu.sd.business.Interfaces.GameGateway;
import ro.utcluj.alexanderstanciu.sd.business.Interfaces.TournamentGateway;
import ro.utcluj.alexanderstanciu.sd.business.Entities.User;
import ro.utcluj.alexanderstanciu.sd.business.Interfaces.UserGateway;
import ro.utcluj.alexanderstanciu.sd.business.Entities.Tournament;
import ro.utcluj.alexanderstanciu.sd.business.Interfaces.MatchGateway;

/**
 *
 * @author XtraSonic
 */
public class Test {
/*
    public static void main(String args[])
    {
        UserGateway ug = new UserDAO();
        
        /*String t = "aa";
        User a= new User(t, t,true);
        a.setPassword(t);
        a.setId(ug.insert(a));
        /*
        t = "b";
        a= new User(t, t,false);
        a.setPassword(t);
        a.setId(ug.insert(a));
        
        t = "c";
        a= new User(t, t,false);
        a.setPassword(t);
        a.setId(ug.insert(a));
        
        t = "d";
        a= new User(t, t,false);
        a.setPassword(t);
        a.setId(ug.insert(a));
        
        t = "e";
        a= new User(t, t,false);
        a.setPassword(t);
        a.setId(ug.insert(a));
        
        
        t = "f";
        a= new User(t, t,false);
        a.setPassword(t);
        a.setId(ug.insert(a));
        
        
        t = "g";
        a= new User(t, t,false);
        a.setPassword(t);
        a.setId(ug.insert(a));
        
        
        t = "h";
        a= new User(t, t,false);
        a.setPassword(t);
        a.setId(ug.insert(a));*/
        
        /*
        Tournament t = new Tournament("Secondino", LocalDate.now(), 2000);
        TournamentGateway tg = new TournamentDAO();
        tg.insert(t);
        */
        /*
        GameGateway gg = new GameDAO();
        Game g = new Game(1,6,7,1);
        /*gg.insert(g);
        g = new Game(1,13,9,1);
        gg.insert(g);
        g = new Game(1,10,11,1);
        gg.insert(g);
        g = new Game(1,12,8,1);
        gg.insert(g);
        
        g = new Game(1,6,13,2);
        gg.insert(g);
        g = new Game(1,11,8,2);
        gg.insert(g);
        
        g = new Game(1,6,11,3);
        gg.insert(g);
        *//*
        
        MatchGateway mg = new MatchDAO();
        Match m;
        m = new Match(6, 5, 7);
        mg.insert(m);
        
    }*/
}
