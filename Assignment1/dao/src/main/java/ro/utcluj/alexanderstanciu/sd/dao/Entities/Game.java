/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.dao.Entities;

/**
 *
 * @author XtraSonic
 */
public class Game {

    public static int UNSET_ID = -1;

    private int id = UNSET_ID;
    private int tournament_id;
    private int player1_id;
    private int player2_id;
    private int level;
    
    public Game(int tournament_id, int player1_id, int player2_id,int level)
    {
        this.tournament_id = tournament_id;
        this.player1_id = player1_id;
        this.player2_id = player2_id;
        this.level = level;
    }

    public Game(int id, int tournament_id, int player1_id, int player2_id,int level)
    {
        this.id = id;
        this.tournament_id = tournament_id;
        this.player1_id = player1_id;
        this.player2_id = player2_id;
        this.level=level;
    }

    public int getId()
    {
        return id;
    }

    public int getTournament_id()
    {
        return tournament_id;
    }

    public int getPlayer1_id()
    {
        return player1_id;
    }

    public int getPlayer2_id()
    {
        return player2_id;
    }

    public int getLevel()
    {
        return level;
    }
    

}
