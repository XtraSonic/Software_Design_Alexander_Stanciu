/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.business;

import java.util.List;
import ro.utcluj.alexanderstanciu.sd.dao.Entities.Game;
import ro.utcluj.alexanderstanciu.sd.dao.Entities.User;

/**
 *
 * @author XtraSonic
 */
public class GameDetails {

    public final int BEST_OF = 5;
    private String player1Name;
    private String player2Name;
    //private int player1Id;
    //private int player2Id;
    private int player1Score;
    private int player2Score;
    private int level;
    private int id;

    public GameDetails(int id, String player1Name, String player2Name, int player1Score, int player2Score, int level)
    {
        this.id = id;
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        //this.player1Id = player1Id;
        //this.player2Id = player2Id;
        this.player1Score = player1Score;
        this.player2Score = player2Score;
        this.level = level;
    }

    @Override
    public String toString()
    {
        return player1Name + " vs. " + player2Name + " (" + player1Score + " - " + player2Score + ")";
    }

    public int getLevel()
    {
        return level;
    }

    /*public boolean isSameGame(Game g)
    {
        return player1Id == g.getPlayer1_id() && player2Id == g.getPlayer2_id()&& level == g.getLevel();
    }*/
    public boolean increseScorePlayer(int winner)
    {
        if (winner == 1)
        {
            player1Score++;
        }
        else
        {
            player2Score++;
        }
        return checkEnd();
    }

    public boolean checkEnd()
    {
        return ((player1Score > BEST_OF / 2) || (player2Score > BEST_OF / 2));
    }

    public int getId()
    {
        return id;
    }

    public String getPlayer1Name()
    {
        return player1Name;
    }

    public String getPlayer2Name()
    {
        return player2Name;
    }

    boolean incrementScore(int winner)
    {
        if (winner == 0)
        {
            return false;
        }
        if (winner == 1)
        {
            player1Score++;
        }
        if (winner == 2)
        {
            player2Score++;
        }
        
        return checkEnd();
    }
    

}
