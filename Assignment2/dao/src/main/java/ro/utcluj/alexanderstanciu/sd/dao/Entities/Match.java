/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.dao.Entities;

import javax.persistence.*;

/**
 *
 * @author XtraSonic
 */
@Entity
@Table(name = "pingpong_match")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name="game_id", nullable=false)
    private Game game;

    @Column(name = "player1_score")
    private int player1Score;

    @Column(name = "player2_score")
    private int player2Score;

    public Match()
    {
    }

    public Match(Game game, int player1Score, int player2Score)
    {
        this.game=game;
        this.player1Score = player1Score;
        this.player2Score = player2Score;
                
    }

    public Match(int id, int game_id, int player1_score, int player2_score)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    public int getId()
    {
        return id;
    }

    public Game getGame()
    {
        return game;
    }

    @Override
    public String toString()
    {
        return "Match{" + "id=" + id + ", game=" + game + ", player1Score=" + player1Score + ", player2Score=" + player2Score + '}';
    }

    public int getPlayer1Score()
    {
        return player1Score;
    }

    public int getPlayer2Score()
    {
        return player2Score;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setGame(Game game)
    {
        this.game = game;
    }

    public void setPlayer1Score(int player1Score)
    {
        this.player1Score = player1Score;
    }

    public void setPlayer2Score(int player2Score)
    {
        this.player2Score = player2Score;
    }

    public int getGameId()
    {
        return game.getId();
    }

    
}
