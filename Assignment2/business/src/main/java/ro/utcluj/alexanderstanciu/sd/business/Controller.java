/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ro.utcluj.alexanderstanciu.sd.dao.Entities.Game;
import ro.utcluj.alexanderstanciu.sd.dao.Entities.Match;
import ro.utcluj.alexanderstanciu.sd.dao.Entities.Tournament;
import implementation.jdbc.JDBCGameDAO;
import implementation.jdbc.JDBCMatchDAO;
import implementation.jdbc.JDBCTournamentDAO;
import implementation.jdbc.JDBCUserDAO;

/**
 *
 * @author XtraSonic
 */
public class Controller {

    private TournamentSession tournamentSession;
    private UserSession userSession;
    private GameSession gameSession;
    private MatchSesion matchSesion;

    public Controller(TournamentSession tournamentSession, UserSession userSession, GameSession gameSession, MatchSesion matchSesion)
    {
        this.tournamentSession = tournamentSession;
        this.userSession = userSession;
        this.gameSession = gameSession;
        this.matchSesion = matchSesion;
    }

    public Controller()
    {
        this.tournamentSession = new TournamentSession(new JDBCTournamentDAO());
        this.userSession = new UserSession(new JDBCUserDAO());
        this.gameSession = new GameSession(new JDBCGameDAO());
        this.matchSesion = new MatchSesion(new JDBCMatchDAO());
    }

    public TournamentSession getTournamentSession()
    {
        return tournamentSession;
    }

    public UserSession getUserSession()
    {
        return userSession;
    }

    public GameSession getGameSession()
    {
        return gameSession;
    }

    public MatchSesion getMatchSesion()
    {
        return matchSesion;
    }

    public List<Tournament> getAllTournaments()
    {
        return tournamentSession.getAllTournaments();
    }

    public void setTournamentById(int id)
    {
        tournamentSession.setTournament(id);
    }

    public String getTournamentName()
    {
        Tournament tournament = tournamentSession.getTournament();
        if (tournament == null)
        {
            return new String("Whoops, A tournament was not found");
        }
        return tournament.getName();
    }

    public List<GameDetails> getGamesFromTournament()
    {
        int tournamentId = tournamentSession.getTournamentId();
        if (tournamentId < 0)
        {
            return null;
        }

        if(gameSession.getCurrentTournamentId() != tournamentId)
            return generateDetailedGames(tournamentId);
        
        
        List<GameDetails> gamesList = gameSession.getGameDetailsList();
        if (gamesList == null)
        {
            return generateDetailedGames(tournamentId);
        }
        else
            return gamesList;
    }

    private List<GameDetails> generateDetailedGames(int tournamentId)
    {
        List<Game> games = gameSession.getAllTournamentGames(tournamentId);
        List<GameDetails> result = new ArrayList();
        List<Integer> scores;
        GameDetails gameDetails;
        for (Game game : games)
        {
            scores = getMatchesWonCounts(game.getId());
            gameDetails = new GameDetails(
                    game.getId(),
                    userSession.getUserById(game.getPlayer1_id()).getName(),
                    userSession.getUserById(game.getPlayer2_id()).getName(),
                    scores.get(0),
                    scores.get(1),
                    game.getLevel());
            result.add(gameDetails);

        }
        gameSession.setGameDetailList(result);
        return result;
    }

    private List<Integer> getMatchesWonCounts(int gameId)
    {
        List<Integer> result = new ArrayList();
        int winsP2 = 0;
        int winsP1 = 0;

        List<Match> matches = matchSesion.getMatchesInGame(gameId);
        for (Match m : matches)
        {
            int winner = matchSesion.checkWinner(m);
            if (winner == 1)
            {
                winsP1++;
            }

            if (winner == 2)
            {
                winsP2++;
            }
        }
        result.add(winsP1);
        result.add(winsP2);
        return result;
    }

    public void setGameById(int id)
    {
        gameSession.setGameById(id);
    }

    public List<Match> getMatchFromGame()
    {
        return matchSesion.getMatchesInGame(gameSession.getGameId());
    }

    public String getCurrentGameName()
    {
        return gameSession.getCurrentGameName();
    }

    public boolean incrementMatchScore(int id)
    {
        int player = getPlayerNumber();
        if (player ==0)
            return false;
        matchSesion.setMatchById(id);
        
        if(matchSesion.checkWinner(matchSesion.getMatch())!=0)
            return false;
        
        int winner = matchSesion.incrementPlayerScore(player);
        if(winner != 0)
        {
            //game not over
            if(!gameSession.updateCurrentGameDetails(winner))
            {
                matchSesion.createNewMatch();
            }
        }
        
        
        return true;
    }

    private int getPlayerNumber()
    {
        Game game = gameSession.getGame();
        if(game.getPlayer1_id() == userSession.getUser().getId())
            return 1;
        if(game.getPlayer2_id() == userSession.getUser().getId())
            return 2;
        return 0;
    }

    public void createNewTournament(String name, LocalDate date, int prize)
    {
        if(userSession.getUser().getIsAdmin())
        {
            tournamentSession.createTournament(name, date, prize);
        }
    }
    
    public boolean getAdminStatus()
    {
        if(userSession.getUser()== null)
            return false;
        return userSession.getUser().getIsAdmin();
    }

    public void createNewUser(String email, String password, boolean admin)
    {
        userSession.createNewUser(email,password,admin);
    }
}
