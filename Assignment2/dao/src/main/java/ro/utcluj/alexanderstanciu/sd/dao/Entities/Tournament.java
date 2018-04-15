/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.dao.Entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author XtraSonic
 */
@Entity
@Table(name = "tournament")
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "start_date")
    @Convert(converter = LocalDateConverter.class)
    private LocalDate startDate;

    @Column(name = "prize_pool")
    private int prizePool;

    @Column(name = "fee")
    private int fee;

    @OneToMany(mappedBy = "tournament")
    private Set<Game> games;

    @ManyToMany(fetch = FetchType.EAGER,targetEntity = User.class, cascade =
        {
            CascadeType.ALL
    })
    @JoinTable(name = "player_in_tournament",
               joinColumns =
               {
                   @JoinColumn(name = "tournament_id")
               },
               inverseJoinColumns =
               {
                   @JoinColumn(name = "user_id")
               })
    private List<User> participants;

    public Tournament()
    {
    }

    public Tournament(int newId, String name, LocalDate date, int prize_pool)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public Tournament(String name, LocalDate date, int fee)
    {
        this.name =name;
        this.startDate=date;
        this.fee = fee;
    }
    
    

    public void setId(int id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setStartDate(LocalDate startDate)
    {
        this.startDate = startDate;
    }

    public void setPrizePool(int prizePool)
    {
        this.prizePool = prizePool;
    }

    public void setFee(int fee)
    {
        this.fee = fee;
    }

    public void setGames(Set<Game> games)
    {
        this.games = games;
    }

    public void setParticipants(List<User> participants)
    {
        this.participants = participants;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public LocalDate getStartDate()
    {
        return startDate;
    }

    public int getPrizePool()
    {
        return prizePool;
    }

    public int getFee()
    {
        return fee;
    }

    public Set<Game> getGames()
    {
        return games;
    }

    public List<User> getParticipants()
    {
        return participants;
    }

    
    public void addUser(User u)
    {
        participants.add(u);
        u.getUserTournaments().add(this);
    }

    
    @Override
    public String toString()
    {
        return "Tournament{" + "id=" + id + ", name=" + name + ", startDate=" + startDate.toString() + ", prizePool=" + prizePool + ", fee=" + fee + '}';
    }

    
}
