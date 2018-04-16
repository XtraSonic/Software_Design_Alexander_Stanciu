/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import ro.utcluj.alexanderstanciu.sd.dao.DAOFactory;
import ro.utcluj.alexanderstanciu.sd.dao.Entities.Game;
import ro.utcluj.alexanderstanciu.sd.dao.Entities.Match;
import ro.utcluj.alexanderstanciu.sd.dao.Entities.Tournament;
import ro.utcluj.alexanderstanciu.sd.dao.Entities.User;

/**
 *
 * @author XtraSonic
 */
public class ModelController {

    private TournamentSession tournamentSession;
    private UserSession userSession;
    private GameSession gameSession;
    private MatchSesion matchSession;
    private DAOFactory factory;

    private static ModelController singleton = null;

    public static ModelController getInstance()
    {
        if (singleton == null)
        {
            singleton = new ModelController();
        }
        return singleton;
    }

    private ModelController()
    {
        factory = DAOFactory.getInstance();
        this.tournamentSession = new TournamentSession(factory.getTournamentGateway());
        this.userSession = new UserSession(factory.getUserGateway());
        this.gameSession = new GameSession(factory.getGameGateway());
        this.matchSession = new MatchSesion(factory.getMatchGateway());
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
        return matchSession;
    }

    public List<Tournament> getAllTournaments()
    {
        return tournamentSession.getAllTournaments();
    }

    public List<User> getAllUsers()
    {
        return userSession.getAllUsers();
    }

    public void setCurrentTournament(int id)
    {
        tournamentSession.setTournament(id);
    }

    public void setCurrentTournament(Tournament tournament)
    {
        tournamentSession.setTournament(tournament);
    }

    public String getCurrentTournamentName()
    {
        Tournament tournament = tournamentSession.getTournament();
        if (tournament == null)
        {
            return "Whoops, A tournament was not found";
        }
        return tournament.getName();
    }

    public List<GameDetails> getGamesFromCurrentTournament()
    {
        int tournamentId = tournamentSession.getTournamentId();
        if (tournamentId < 0)
        {
            return null;
        }

        if (gameSession.getCurrentGameTournamentId() != tournamentId)
        {
            return generateDetailedGames(tournamentId);
        }

        List<GameDetails> gamesList = gameSession.getGameDetailsList();
        if (gamesList == null)
        {
            return generateDetailedGames(tournamentId);
        }
        else
        {
            return gamesList;
        }
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
                    userSession.getUserNameById(game.getPlayer1Id()),
                    userSession.getUserNameById(game.getPlayer2Id()),
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

        List<Match> matches = matchSession.getMatchesInGame(gameId);
        for (Match m : matches)
        {
            int winner = matchSession.checkWinner(m);
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
        return matchSession.getMatchesInGame(gameSession.getGameId());
    }

    public String getCurrentGameName()
    {
        return gameSession.getCurrentGameName();
    }

    public boolean incrementMatchScore(int id)
    {
        int player = getPlayerNumber();
        if (player == 0)
        {
            return false;
        }
        matchSession.setMatchById(id);

        //match didn't end already
        if (matchSession.checkWinner(matchSession.getMatch()) != 0)
        {
            return false;
        }

        int winner = matchSession.incrementPlayerScore(player);
        if (winner != 0)
        {
            //game not over
            if (!gameSession.updateCurrentGameDetails(winner))
            {
                matchSession.createNewMatch(gameSession.getGame());
            }
            else
            {
                if(gameSession.getCurrentGameDetails().getLevel() == 3)
                {
                    userSession.updateMoney(userSession.getUser(), 
                                            tournamentSession.getTournament()
                                                    .getPrizePool());
                }
            }
        }

        return true;
    }

    private int getPlayerNumber()
    {
        Game game = gameSession.getGame();
        if (game.getPlayer1Id() == userSession.getUser().getId())
        {
            return 1;
        }
        if (game.getPlayer2Id() == userSession.getUser().getId())
        {
            return 2;
        }
        return 0;
    }

    public void createNewTournament(String name, LocalDate date, int fee)
    {
        if(date.isBefore(LocalDate.now().plusMonths(1)))
            return;
        
        if (userSession.getUser().getIsAdmin())
        {
            tournamentSession.createTournament(name, date, fee);
        }
    }

    public boolean getAdminStatus()
    {
        if (userSession.getUser() == null)
        {
            return false;
        }
        return userSession.getUser().getIsAdmin();
    }

    public void createNewUser(String email, String password, boolean admin)
    {
        userSession.createNewUser(email, password, admin);
    }

    public boolean logIn(String email, String password)
    {
        return userSession.logIn(email, password);
    }

    public void close()
    {
        factory.closeConnection();
    }

    public void logOut()
    {
        userSession.logOut();
    }

    public void deselectTournament()
    {
        tournamentSession.deselectTournament();
    }

    public void deselectMatch()
    {
        matchSession.deselectMatch();
    }

    public void addMatchObserver(Observer aThis)
    {
        matchSession.addObserver(aThis);
    }

    public void addGameObserver(Observer aThis)
    {
        gameSession.addObserver(aThis);
    }

    public void removeMatchObserver(Observer aThis)
    {
        matchSession.deleteObserver(aThis);
    }

    public void removeGameObserver(Observer aThis)
    {
        gameSession.deleteObserver(aThis);
    }

    public String enroll()
    {
        User u = userSession.getUser();
        if (u == null)
        {
            return "No User Selected";
        }
        return tournamentSession.enroll(u);
    }

    public int getUserBalance()
    {
        return userSession.getBalance();
    }

    public boolean isEnrolled(Tournament t)
    {
        if (userSession.getUser() == null)
        {
            return false;
        }
        return tournamentSession.isEnrolled(userSession.getUser(), t);
    }

    public void updateUserMoney(User u, int ammount)
    {
        userSession.updateMoney(u, ammount);
    }

    public void addUserObserver(Observer aThis)
    {
        userSession.addObserver(aThis);
    }

    public void addTournamentObserver(Observer aThis)
    {
        tournamentSession.addObserver(aThis);
    }

    public boolean hasTournamentEnded(Tournament t)
    {
        return t.getGames()
                .stream()
                .filter(game -> game.getLevel() == 3)
                .anyMatch((game) ->
                {
                    List<Integer> scores;
                    scores = getMatchesWonCounts(game.getId());
                    GameDetails gameDetails = new GameDetails(
                            game.getId(),
                            game.getPlayer1().getEmail(),
                            game.getPlayer2().getEmail(),
                            scores.get(0),
                            scores.get(1),
                            game.getLevel());
                    return gameDetails.checkEnd();
                });
    }

    public void createNewGame(Tournament t, User u1, User u2, int level)
    {
        Match m = gameSession.createNewGame(t,u1,u2,level);
        matchSession.insertNewMatch(m);
    }

}
