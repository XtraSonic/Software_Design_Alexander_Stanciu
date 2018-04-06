/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.business;

import java.util.List;
import ro.utcluj.alexanderstanciu.sd.dao.Entities.Match;
import ro.utcluj.alexanderstanciu.sd.dao.Interfaces.MatchGateway;

/**
 *
 * @author XtraSonic
 */
public class MatchSesion {
    private MatchGateway gateway;
    private Match match;
    private final int MINIMUM_TO_WIN = 11;

    public MatchSesion(MatchGateway gateway)
    {
        this.gateway = gateway;
    }
    
    public List<Match> getMatchesInGame(int gameId)
    {
        return gateway.findMatchesInGame(gameId);
    }

    public int checkWinner(Match m)
    {
        int pl1score =m.getPlayer1_score();
        int pl2score =m.getPlayer2_score();
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
            match.setPlayer1_score(match.getPlayer1_score()+1);
            gateway.update(match);   
        }
        if(player == 2)
        {
            match.setPlayer2_score(match.getPlayer2_score()+1);
            gateway.update(match);
        }
        return checkWinner(match);
    }

    void createNewMatch()
    {
        match = new Match(match.getGame_id(), 0, 0);
        gateway.insert(match);
    }
    
    
    
}
