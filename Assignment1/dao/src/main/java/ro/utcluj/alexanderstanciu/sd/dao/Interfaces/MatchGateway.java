/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.dao.Interfaces;

import java.util.List;
import ro.utcluj.alexanderstanciu.sd.dao.Entities.Match;

/**
 *
 * @author XtraSonic
 */
public interface MatchGateway extends GeneralGateway<Match> {
    public List<Match> findMatchesInGame(int gameId);
}
