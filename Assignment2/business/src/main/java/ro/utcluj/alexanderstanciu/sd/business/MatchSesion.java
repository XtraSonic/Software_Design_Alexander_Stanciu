/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.business;

import java.util.List;
import java.util.Observable;
import ro.utcluj.alexanderstanciu.sd.dao.Entities.Game;
import ro.utcluj.alexanderstanciu.sd.dao.Entities.Match;
import ro.utcluj.alexanderstanciu.sd.dao.Interfaces.MatchGateway;

/**
 *
 * @author XtraSonic
 */
public class MatchSesion extends Observable{
    private MatchGateway gateway;
    private Match match;
    private final int MINIMUM_TO_WIN = 11;

    public MatchSesion(MatchGateway gateway)
    {
        super();
        this.gateway = gateway;
    }
    
    public List<Match> getMatchesInGame(int gameId)
    {
        return gateway.findMatchesInGame(gameId);
    }

    public int checkWinner(Match m)
    {
        int pl1score =m.getPlayer1Score();
        int pl2score =m.getPlayer2Score();
        if(pl2score<MINIMUM_TO_WIN && pl1score<MINIMUM_TO_WIN)
            return 0;
        else
            if(pl1score > pl2score)
                if(pl1score - pl2score>=2)
                    return 1;
                else
                    return 0;
            else
                if(pl2score - pl1score>=2)
                    return 2;
                else
                    return 0;
            
    }

    void setMatchById(int id)
    {
        match = gateway.findById(id);
        setChanged();
    }

    public Match getMatch()
    {
        return match;
    }

    /**
     * 
     * @param player
     * @return the nr of the player who won the match
     */
    int incrementPlayerScore(int player)
    {
        if(player == 1)
        {
            match.setPlayer1Score(match.getPlayer1Score()+1);
            gateway.update(match);   
            setChanged();
        }
        if(player == 2)
        {
            match.setPlayer2Score(match.getPlayer2Score()+1);
            gateway.update(match);
            setChanged();
        }
        return checkWinner(match);
    }

    void createNewMatch(Game g)
    {
        match = new Match(g, 0, 0);
        gateway.insert(match);
    }
    
    
    
}
