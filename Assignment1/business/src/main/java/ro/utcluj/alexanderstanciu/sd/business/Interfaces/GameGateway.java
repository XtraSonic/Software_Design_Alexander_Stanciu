/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.business.Interfaces;

import java.util.List;
import ro.utcluj.alexanderstanciu.sd.business.Entities.Game;

/**
 *
 * @author XtraSonic
 */
public interface GameGateway extends GeneralGateway<Game> {
    public List<Game> getGamesInTournament(int id);
}
