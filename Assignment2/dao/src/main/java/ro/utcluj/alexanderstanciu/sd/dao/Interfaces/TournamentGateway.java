/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.dao.Interfaces;

import java.util.List;
import java.util.Set;
import ro.utcluj.alexanderstanciu.sd.dao.Entities.Tournament;
import ro.utcluj.alexanderstanciu.sd.dao.Entities.User;

/**
 *
 * @author XtraSonic
 */
public interface TournamentGateway extends GeneralGateway<Tournament> {
    public void enrollUserInTournament(User u,Tournament t);

}
