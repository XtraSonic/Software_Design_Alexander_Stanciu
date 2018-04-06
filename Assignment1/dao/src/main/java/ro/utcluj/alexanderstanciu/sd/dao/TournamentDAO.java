/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import ro.utcluj.alexanderstanciu.sd.business.Interfaces.TournamentGateway;
import ro.utcluj.alexanderstanciu.sd.business.Entities.Tournament;

/**
 *
 * @author XtraSonic
 */
public class TournamentDAO implements TournamentGateway {

    @Override
    public int insert(Tournament tournament)
    {
        String sqlQuery = "INSERT INTO `tournament` (`name`, `date`, `prize_pool`) VALUES (?, ?, ?);";
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sqlQuery);

            statement.setString(1, tournament.getName());
            statement.setObject(2, Date.valueOf(tournament.getDate()));
            statement.setInt(3, tournament.getPrize_pool());

            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next())
            {
                return (resultSet.getInt(1));
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
    public void update(Tournament tournament)
    {
        Connection connection = null;
        PreparedStatement statement = null;
        String sqlQuery = "UPDATE `tournament` SET  `name`=?, `date`=?, `prize_pool`=? WHERE `id`=?;";

        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, tournament.getName());
            statement.setObject(2, tournament.getDate());
            statement.setInt(3, tournament.getPrize_pool());
            statement.setInt(4, tournament.getId());

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
    public void delete(Tournament tournament)
    {
        Connection connection = null;
        PreparedStatement statement = null;
        String sqlQuery = "DELETE FROM `tournament` WHERE `id`=?;";
        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, tournament.getId());
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
    public Tournament findById(int id)
    {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;
        Tournament tournament = null;
        String sqlQuery = "SELECT * FROM `tournament` WHERE `id`=?;";
        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                int newId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                LocalDate date = resultSet.getDate("date").toLocalDate();
                int prize_pool = resultSet.getInt("prize_pool");

                tournament = new Tournament(newId, name, date, prize_pool);
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
        return tournament;
    }

    @Override
    public List<Tournament> findAll()
    {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;
        ArrayList<Tournament> tournamentList = new ArrayList();

        String sqlQuery = "SELECT * FROM `tournament`;";
        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sqlQuery);
            resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                LocalDate date = resultSet.getDate("date").toLocalDate();
                int prize_pool = resultSet.getInt("prize_pool");

                tournamentList.add(new Tournament(id, name, date, prize_pool));
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
        return tournamentList;
    }
    
    
}
