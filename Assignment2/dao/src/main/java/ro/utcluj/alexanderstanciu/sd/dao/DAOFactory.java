/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.dao;

import implementation.jdbc.JdbcGatewayFactory;
import implementation.hibernate.HibernateGatewayFactory;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import org.json.JSONObject;
import ro.utcluj.alexanderstanciu.sd.dao.Interfaces.GameGateway;
import ro.utcluj.alexanderstanciu.sd.dao.Interfaces.MatchGateway;
import ro.utcluj.alexanderstanciu.sd.dao.Interfaces.TournamentGateway;
import ro.utcluj.alexanderstanciu.sd.dao.Interfaces.UserGateway;

/**
 *
 * @author XtraSonic
 */
public abstract class DAOFactory {

    private static String configFileName = "config.json";

    public enum Type {
        HIBERNATE,
        JDBC;
    }

    protected DAOFactory()
    {

    }

    public static DAOFactory getInstance(Type factoryType)
    {
        switch (factoryType)
        {
            case HIBERNATE:
                return new HibernateGatewayFactory();
            case JDBC:
                return new JdbcGatewayFactory();
            default:
                throw new IllegalArgumentException("Invalid factory");
        }
    }

    public static DAOFactory getInstance()
    {
        // read from the URL
        String str = new String();
        String line = new String();

        try
        {
            try (FileReader fileReader = new FileReader(configFileName);
                 BufferedReader bufferedReader = new BufferedReader(fileReader))
            {
                while ((line = bufferedReader.readLine()) != null)
                {
                    str += line;
                }
                JSONObject obj = new JSONObject(str);
                if (obj.get("DAOType").equals("JDBC"))
                {
                    return getInstance(Type.JDBC);
                }
                if (obj.get("DAOType").equals("HIBERNATE"))
                {
                    return getInstance(Type.HIBERNATE);
                }

            }
        }
        catch (FileNotFoundException ex)
        {
            try
            {
                FileWriter fileWriter = new FileWriter(configFileName);
                try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter))
                {
                    bufferedWriter.write("{");
                    bufferedWriter.newLine();
                    bufferedWriter.write("	\"DAOType\": \"HIBERNATE\"");
                    bufferedWriter.newLine();
                    bufferedWriter.write("}");
                }
            }
            catch (IOException ex2)
            {
                System.out.println("Error creating config file");
            }
        }
        catch (IOException ex)
        {
            System.out.println("Error reading file config fole");
        }

        return getInstance(Type.HIBERNATE);
    }

    public abstract GameGateway getGameGateway();

    public abstract UserGateway getUserGateway();

    public abstract TournamentGateway getTournamentGateway();

    public abstract MatchGateway getMatchGateway();

    public abstract void closeConnection();
}
