/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.business;

import java.time.LocalDate;
import java.util.List;
import ro.utcluj.alexanderstanciu.sd.dao.Entities.Tournament;
import ro.utcluj.alexanderstanciu.sd.dao.Interfaces.TournamentGateway;

/**
 *
 * @author XtraSonic
 */
public class TournamentSession {
    private TournamentGateway gateway;
    private Tournament tournament;
    
    public TournamentSession(TournamentGateway gateway)
    {
        this.gateway = gateway;
    }

    public int getTournamentId()
    {
        if(tournament == null)
            return -1;
        return tournament.getId();
    }
    
    public List<Tournament> getAllTournaments()
    {
        return gateway.findAll();
    }


    public void setTournament(int id)
    {
        this.tournament = gateway.findById(id);
    }

    Tournament getTournament()
    {
        return tournament;
    }

    void createTournament(String name, LocalDate date, int prize)
    {
        gateway.insert(new Tournament(name, date, prize));
    }
    
    
}
