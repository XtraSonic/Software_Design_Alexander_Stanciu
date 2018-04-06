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
public class Match {
    
    public static int UNSET_ID = -1;

    private int id = UNSET_ID;
    private int game_id;
    private int player1_score;
    private int player2_score;

    public Match(int game_id, int player1_score, int player2_score)
    {
        this.game_id = game_id;
        this.player1_score = player1_score;
        this.player2_score = player2_score;
    }

    public Match(int id, int game_id, int player1_score, int player2_score)
    {
        this.id = id;
        this.game_id = game_id;
        this.player1_score = player1_score;
        this.player2_score = player2_score;
    }

    public int getId()
    {
        return id;
    }

    public int getGame_id()
    {
        return game_id;
    }

    public int getPlayer1_score()
    {
        return player1_score;
    }

    public int getPlayer2_score()
    {
        return player2_score;
    }

    public void setPlayer1_score(int player1_score)
    {
        if(player1_score>0)
            this.player1_score = player1_score;
    }

    public void setPlayer2_score(int player2_score)
    {
        if(player2_score>0)
            this.player2_score = player2_score;
    }
    
    
    
}
