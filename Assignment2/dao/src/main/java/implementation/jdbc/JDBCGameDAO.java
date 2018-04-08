/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementation.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ro.utcluj.alexanderstanciu.sd.dao.Entities.Game;
import ro.utcluj.alexanderstanciu.sd.dao.Interfaces.GameGateway;
/**
 *
 * @author XtraSonic
 */
public class JDBCGameDAO implements GameGateway {

    @Override
    public int insert(Game game)
    {
        String sqlQuery = "INSERT INTO `game` (`tournament_id`, `player1_id`, `player2_id`, `level`) VALUES (?, ?, ?,?);";
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sqlQuery);

            statement.setInt(1, game.getTournamentId());
            statement.setInt(2, game.getPlayer1Id());
            statement.setInt(3, game.getPlayer2Id());
            statement.setInt(4, game.getLevel());

            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next())
            {
                return resultSet.getInt(1);
            }

        }
        catch (SQLException | SecurityException | IllegalArgumentException ex)
        {
            System.out.println(ex.getMessage());
        }
        finally
        {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return -1;
    }

    @Override
    public void update(Game game)
    {
        Connection connection = null;
        PreparedStatement statement = null;
        String sqlQuery = "UPDATE `game` SET  `tournament_id`=?, `player1_id`=?, `player2_id`=? `level`=? WHERE `id`=?;";

        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, game.getTournamentId());
            statement.setInt(2, game.getPlayer1Id());
            statement.setInt(3, game.getPlayer2Id());
            statement.setInt(3, game.getLevel());
            statement.setInt(5, game.getId());

            statement.executeUpdate();

        }
        catch (SQLException | IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    @Override
    public void delete(Game game)
    {
        Connection connection = null;
        PreparedStatement statement = null;
        String sqlQuery = "DELETE FROM `game` WHERE `id`=?;";
        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, game.getId());
            statement.executeUpdate();
        }
        catch (SQLException | IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    @Override
    public Game findById(int id)
    {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;
        Game game = null;
        String sqlQuery = "SELECT * FROM `game` WHERE `id`=?;";
        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                int newId = resultSet.getInt("id");
                int tournament_id = resultSet.getInt("tournament_id");
                int player1_id = resultSet.getInt("player1_id");
                int player2_id = resultSet.getInt("player2_id");
                int level = resultSet.getInt("level");

                game = new Game(newId, tournament_id, player1_id, player2_id, level);
            }

        }
        catch (SQLException | IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return game;
    }

    @Override
    public List<Game> findAll()
    {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;
        ArrayList<Game> gameList = new ArrayList();

        String sqlQuery = "SELECT * FROM `game`";
        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sqlQuery);
            resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                int id = resultSet.getInt("id");
                int tournament_id = resultSet.getInt("tournament_id");
                int player1_id = resultSet.getInt("player1_id");
                int player2_id = resultSet.getInt("player2_id");
                int level = resultSet.getInt("level");

                gameList.add(new Game(id, tournament_id, player1_id, player2_id, level));
            }

        }
        catch (SQLException | IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return gameList;
    }

    @Override
    public List<Game> getGamesInTournament(int tournamentId)
    {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;
        ArrayList<Game> gameList = new ArrayList();

        String sqlQuery = "SELECT * FROM `game` WHERE `tournament_id`=?;";
        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, tournamentId);
            resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                int id = resultSet.getInt("id");
                int tournament_id = resultSet.getInt("tournament_id");
                int player1_id = resultSet.getInt("player1_id");
                int player2_id = resultSet.getInt("player2_id");
                int level = resultSet.getInt("level");

                gameList.add(new Game(id, tournament_id, player1_id, player2_id, level));
            }

        }
        catch (SQLException | IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return gameList;
    }

    @Override
    public void closeConnection()
    {
        throw new UnsupportedOperationException("Not supported yet.");
        //TODO
    }
}
