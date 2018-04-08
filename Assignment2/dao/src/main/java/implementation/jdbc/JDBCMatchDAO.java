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
import ro.utcluj.alexanderstanciu.sd.dao.Entities.Match;
import ro.utcluj.alexanderstanciu.sd.dao.Interfaces.MatchGateway;

/**
 *
 * @author XtraSonic
 */
public class JDBCMatchDAO implements MatchGateway{
    
    @Override
    public int insert(Match pingpong_match)
    {
        String sqlQuery = "INSERT INTO `pingpong_match` (`game_id`, `player1_score`, `player2_score`) VALUES (?, ?, ?);";
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sqlQuery);

            statement.setInt(1, pingpong_match.getGameId());
            statement.setInt(2, pingpong_match.getPlayer1Score());
            statement.setInt(3, pingpong_match.getPlayer2Score());

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
    public void update(Match pingpong_match)
    {
        Connection connection = null;
        PreparedStatement statement = null;
        String sqlQuery = "UPDATE `pingpong_match` SET  `game_id`=?, `player1_score`=?, `player2_score`=? WHERE `id`=?;";

        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, pingpong_match.getGameId());
            statement.setInt(2, pingpong_match.getPlayer1Score());
            statement.setInt(3, pingpong_match.getPlayer2Score());
            statement.setInt(4, pingpong_match.getId());

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
    public void delete(Match pingpong_match)
    {
        Connection connection = null;
        PreparedStatement statement = null;
        String sqlQuery = "DELETE FROM `pingpong_match` WHERE `id`=?;";
        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, pingpong_match.getId());
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
    public Match findById(int id)
    {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;
        Match pingpong_match = null;
        String sqlQuery = "SELECT * FROM `pingpong_match` WHERE `id`=?;";
        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                int newId = resultSet.getInt("id");
                int game_id = resultSet.getInt("game_id");
                int player1_score = resultSet.getInt("player1_score");
                int player2_score = resultSet.getInt("player2_score");

                pingpong_match = new Match(newId, game_id, player1_score, player2_score);
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
        return pingpong_match;
    }

    @Override
    public List<Match> findAll()
    {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;
        ArrayList<Match> pingpong_matchList = new ArrayList();

        String sqlQuery = "SELECT * FROM `pingpong_match`";
        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sqlQuery);
            resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                int id = resultSet.getInt("id");
                int game_id = resultSet.getInt("game_id");
                int player1_score = resultSet.getInt("player1_score");
                int player2_score = resultSet.getInt("player2_score");

                pingpong_matchList.add(new Match(id, game_id, player1_score, player2_score));
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
        return pingpong_matchList;
    }
    
     @Override
    public List<Match> findMatchesInGame(int gameId)
    {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;
        ArrayList<Match> pingpong_matchList = new ArrayList();

        String sqlQuery = "SELECT * FROM `pingpong_match` WHERE `game_id`=?";
        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, gameId);
            resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                int id = resultSet.getInt("id");
                int game_id = resultSet.getInt("game_id");
                int player1_score = resultSet.getInt("player1_score");
                int player2_score = resultSet.getInt("player2_score");

                pingpong_matchList.add(new Match(id, game_id, player1_score, player2_score));
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
        return pingpong_matchList;
    }

    @Override
    public void closeConnection()
    {
        throw new UnsupportedOperationException("Not supported yet.");
        //TODO
    }
}
