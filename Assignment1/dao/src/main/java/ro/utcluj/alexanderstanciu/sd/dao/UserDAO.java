/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ro.utcluj.alexanderstanciu.sd.business.Entities.User;
import ro.utcluj.alexanderstanciu.sd.business.Interfaces.UserGateway;

/**
 *
 * @author XtraSonic
 */
public class UserDAO implements UserGateway {

    @Override
    public int insert(User user)
    {
        String sqlQuery = "INSERT INTO `user` (`email`, `password`, `is_admin`) VALUES (?, ?, ?);";
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sqlQuery);

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setBoolean(3, user.getIsAdmin());

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
    public void update(User user)
    {
        Connection connection = null;
        PreparedStatement statement = null;
        String sqlQuery = "UPDATE `user` SET  `email`=?, `password`=?, `is_admin`=? WHERE `id`=?;";

        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setBoolean(3, user.getIsAdmin());
            statement.setInt(4, user.getId());

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
    public void delete(User user)
    {
        Connection connection = null;
        PreparedStatement statement = null;
        String sqlQuery = "DELETE FROM `user` WHERE `id`=?;";
        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, user.getId());
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
    public User findById(int id)
    {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;
        User user = null;
        String sqlQuery = "SELECT * FROM `user` WHERE `id`=?;";
        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                int newId = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                boolean is_admin = resultSet.getBoolean("is_admin");

                user = new User(newId, email, password, is_admin);
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
        return user;
    }

    @Override
    public List<User> findAll()
    {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;
        ArrayList<User> userList = new ArrayList();

        String sqlQuery = "SELECT * FROM `user`;";
        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sqlQuery);
            resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                int id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                boolean is_admin = resultSet.getBoolean("is_admin");

                userList.add(new User(id, email, password, is_admin));
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
        return userList;
    }

    @Override
    public User findByEmail(String email)
    {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;
        User user = null;
        String sqlQuery = "SELECT * FROM `user` WHERE `email`=?;";
        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                int id = resultSet.getInt("id");
                String password = resultSet.getString("password");
                boolean is_admin = resultSet.getBoolean("is_admin");

                user = new User(id, email, password, is_admin);
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
        return user;
    }

}
