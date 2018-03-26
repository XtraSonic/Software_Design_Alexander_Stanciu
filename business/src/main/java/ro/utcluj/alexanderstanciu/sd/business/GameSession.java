/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.business;

import java.util.List;
import ro.utcluj.alexanderstanciu.sd.business.Entities.Game;
import ro.utcluj.alexanderstanciu.sd.business.Interfaces.GameGateway;

/**
 *
 * @author XtraSonic
 */
public class GameSession {
    private GameGateway gateway;
    private Game game;
    private List<GameDetails> gameDetailList;
    private int currentTournamentId;

    public GameSession(GameGateway gateway)
    {
        this.gateway = gateway;
    }
    
    public List<Game> getAllTournamentGames(int tournamentId)
    {
        currentTournamentId = tournamentId;
        return gateway.getGamesInTournament(tournamentId);
    }

    public void setGameById(int id)
    {
        game = gateway.findById(id);
    }

    public int getGameId()
    {
        return game.getId();
    }

    public void setGameDetailList(List<GameDetails> result)
    {
        this.gameDetailList = result;
    }
    
    public List<GameDetails> getGameDetailsList()
    {
        if(gameDetailList == null)
            return null;
        else
            return gameDetailList;
    }
    
    public GameDetails getCurrentGameDetails()
    {
        if(game == null)
            return null;
        
        for(GameDetails gd :gameDetailList)
        {
            if(gd.getId() == game.getId())
                return gd;
        } 
        return null;
    }
    
    public String getCurrentGameName()
    {
        GameDetails gd = getCurrentGameDetails();
        if(gd == null)
            return "Whoops, No Game details available";
        
        if(gd.checkEnd())
        {
            return gd.getPlayer1Name() +" vs. "+ gd.getPlayer2Name() + " (Finalized)";
        }
        else
        {
            return gd.getPlayer1Name() +" vs. "+ gd.getPlayer2Name() + " (Ongoing)";
        }
        
    }

    public int getCurrentTournamentId()
    {
        return currentTournamentId;
    }

    public Game getGame()
    {
        return game;
    }

    boolean updateCurrentGameDetails(int winner)
    {
        if(winner == 0)
        {
            return false;
        }
        return getCurrentGameDetails().incrementScore(winner);
    }
    
}
