/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.business;

import java.time.LocalDate;
import java.util.List;
import java.util.Observable;
import ro.utcluj.alexanderstanciu.sd.dao.Entities.Tournament;
import ro.utcluj.alexanderstanciu.sd.dao.Entities.User;
import ro.utcluj.alexanderstanciu.sd.dao.Interfaces.TournamentGateway;

/**
 *
 * @author XtraSonic
 */
public class TournamentSession extends Observable {

    private TournamentGateway gateway;
    private Tournament tournament;
    private int MAX_PARTICIPANTS = 8;

    public TournamentSession(TournamentGateway gateway)
    {
        super();
        this.gateway = gateway;
    }

    public int getTournamentId()
    {
        if (tournament == null)
        {
            return -1;
        }
        return tournament.getId();
    }

    public List<Tournament> getAllTournaments()
    {
        return gateway.findAll();
    }

    public void setTournament(int id)
    {
        this.tournament = gateway.findById(id);
        setChanged();
        notifyObservers();
    }

    public void setTournament(Tournament tournament)
    {
        this.tournament = tournament;
        setChanged();
        notifyObservers();
    }

    Tournament getTournament()
    {
        return tournament;
    }

    void createTournament(String name, LocalDate date, int fee)
    {
        gateway.insert(new Tournament(name, date, fee));
    }

    void deselectTournament()
    {
        tournament = null;
    }

    public String enroll(User u)
    {
        if(tournament == null)
            return "No Turnament selected";
        LocalDate startDate =tournament.getStartDate();
        if(startDate.isBefore(LocalDate.now()))
            return "Turnament started, can't enroll now";
        
        if(tournament.getParticipants().size() > MAX_PARTICIPANTS)
            return "Tournament is full";
        
        if(u.getMoney() < tournament.getFee())
            return "Insuficient Funds";
        
        u.setMoney(u.getMoney() - tournament.getFee());
        tournament.setPrizePool(tournament.getPrizePool() + tournament.getFee());
        if(!tournament.addUser(u))
        {
            return "Already Enrolled";
        }
        gateway.enrollUserInTournament(u, tournament);
        setChanged();
        notifyObservers();
        return "Succesfully enrolled";
    }

    boolean isEnrolled(User user,Tournament t)
    {
        if(t == null)
            return false;
        else
        {    
            //boolean tt = t.getParticipants().contains(user);
            boolean tt = t.containsUser(user);
            return tt;
        }
    }
    
    
}
