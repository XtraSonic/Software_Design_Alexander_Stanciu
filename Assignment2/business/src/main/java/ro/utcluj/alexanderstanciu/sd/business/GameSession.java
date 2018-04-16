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
import ro.utcluj.alexanderstanciu.sd.dao.Entities.Tournament;
import ro.utcluj.alexanderstanciu.sd.dao.Entities.User;
import ro.utcluj.alexanderstanciu.sd.dao.Interfaces.GameGateway;

/**
 *
 * @author XtraSonic
 */
public class GameSession extends Observable {

    private GameGateway gateway;
    private Game game;
    private List<GameDetails> gameDetailList;

    public GameSession(GameGateway gateway)
    {
        super();
        this.gateway = gateway;
    }

    public List<Game> getAllTournamentGames(int tournamentId)
    {
        return gateway.getGamesInTournament(tournamentId);
    }

    public void setGameById(int id)
    {
        game = gateway.findById(id);
        setChanged();
        notifyObservers();
    }

    public int getGameId()
    {
        return game.getId();
    }

    public void setGameDetailList(List<GameDetails> result)
    {
        this.gameDetailList = result;
        setChanged();
        notifyObservers();
    }

    public List<GameDetails> getGameDetailsList()
    {
        if (gameDetailList == null)
        {
            return null;
        }
        else
        {
            return gameDetailList;
        }
    }

    public GameDetails getCurrentGameDetails()
    {
        if (game == null)
        {
            return null;
        }

        for (GameDetails gd : gameDetailList)
        {
            if (gd.getId() == game.getId())
            {
                return gd;
            }
        }
        return null;
    }

    public String getCurrentGameName()
    {
        GameDetails gd = getCurrentGameDetails();
        if (gd == null)
        {
            return "Whoops, No Game details available";
        }

        if (gd.checkEnd())
        {
            return gd.getPlayer1Name() + " vs. " + gd.getPlayer2Name() + " (Finalized)";
        }
        else
        {
            return gd.getPlayer1Name() + " vs. " + gd.getPlayer2Name() + " (Ongoing)";
        }

    }

    public int getCurrentGameTournamentId()
    {
        if (game == null)
        {
            return -1;
        }

        return game.getTournamentId();
    }

    public Game getGame()
    {
        return game;
    }

    boolean updateCurrentGameDetails(int winner)
    {
        if (winner == 0)
        {
            return false;
        }
        boolean outcome = getCurrentGameDetails().incrementScore(winner);
        setChanged();
        notifyObservers();
        return outcome;
    }

    Match createNewGame(Tournament t, User u1, User u2, int level)
    {
        Game g = new Game(t,u1,u2,level);
        Match m = new Match(g,0,0);
        g.addMatch(m);
        t.addGame(g);
        u1.addGameP1(g);
        u2.addGameP2(g);
        gateway.insert(g);
        return m;
    }


}
