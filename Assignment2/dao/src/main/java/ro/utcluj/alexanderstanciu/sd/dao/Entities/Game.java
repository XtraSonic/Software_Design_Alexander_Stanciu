/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.dao.Entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author XtraSonic
 */
@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player1_id", nullable=false)
    private User player1;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player2_id", nullable=false)
    private User player2;

    @Column(name = "level")
    private int level;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="tournament_id", nullable=false)
    private Tournament tournament;

    
    @OneToMany(fetch = FetchType.EAGER,mappedBy="game")
    private Set<Match> matches;
    
    public Game()
    {
    }

    public Game(int id, User player1, User player2, int level, Tournament tournament, Set<Match> matches)
    {
        this.id = id;
        this.player1 = player1;
        this.player2 = player2;
        this.level = level;
        this.tournament = tournament;
        this.matches = matches;
    }

    public Game(int newId, int tournament_id, int player1_id, int player2_id, int level)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Game(Tournament t, User u1, User u2, int level)
    {
        this.player1 = u1;
        this.player2 = u2;
        this.level = level;
        this.tournament = t;
    }
    
    

    public void setId(int id)
    {
        this.id = id;
    }

    public void setPlayer1(User player1)
    {
        this.player1 = player1;
    }

    public void setPlayer2(User player2)
    {
        this.player2 = player2;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public void setTournament(Tournament tournament)
    {
        this.tournament = tournament;
    }

    public void setMatches(Set<Match> matches)
    {
        this.matches = matches;
    }

    public int getId()
    {
        return id;
    }

    public User getPlayer1()
    {
        return player1;
    }

    public User getPlayer2()
    {
        return player2;
    }

    public int getLevel()
    {
        return level;
    }

    public Tournament getTournament()
    {
        return tournament;
    }

    public Set<Match> getMatches()
    {
        return matches;
    }

    public int getTournamentId()
    {
        return tournament.getId();
    }

    public int getPlayer1Id()
    {
        return player1.getId();
    }
    
    public int getPlayer2Id()
    {
        return player2.getId();
    }

    @Override
    public String toString()
    {
        return "Game{" + "id=" + id + ", player1=" + player1 + ", player2=" + player2 + ", level=" + level + ", tournament=" + tournament + ", matches=" + matches + '}';
    }

    public void addMatch(Match m)
    {
        if(matches==null)
        {
            matches =  new HashSet<>();
        }
        matches.add(m);
    }

    
}
