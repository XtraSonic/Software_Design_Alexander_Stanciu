/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.dao;

import implementation.jdbc.JdbcGatewayFactory;
import implementation.hibernate.HibernateGatewayFactory;
import ro.utcluj.alexanderstanciu.sd.dao.Entities.Tournament;
import ro.utcluj.alexanderstanciu.sd.dao.Interfaces.GameGateway;
import ro.utcluj.alexanderstanciu.sd.dao.Interfaces.MatchGateway;
import ro.utcluj.alexanderstanciu.sd.dao.Interfaces.TournamentGateway;
import ro.utcluj.alexanderstanciu.sd.dao.Interfaces.UserGateway;

/**
 *
 * @author XtraSonic
 */
public abstract class DAOFactory {
    public enum Type {
		HIBERNATE,
		JDBC;
	}


	protected DAOFactory(){

	}

	public static DAOFactory getInstance(Type factoryType) {
		switch (factoryType) {
			case HIBERNATE:
				return new HibernateGatewayFactory();
			case JDBC:
				return new JdbcGatewayFactory();
			default:
				throw new IllegalArgumentException("Invalid factory");
		}
	}

	public abstract GameGateway getGameGateway();

	public abstract UserGateway getUserGateway();
        
	public abstract TournamentGateway getTournamentGateway();

	public abstract MatchGateway getMatchGateway();
}
