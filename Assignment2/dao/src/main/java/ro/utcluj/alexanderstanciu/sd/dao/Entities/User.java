/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.dao.Entities;

import java.util.Base64;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author XtraSonic
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "is_admin")
    private boolean isAdmin = false;

    @Column(name = "money")
    private int money;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "player1")
    private Set<Game> gamesAsPlayer1;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "player2")
    private Set<Game> gamesAsPlayer2;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "participants")
    List<Tournament> userTournaments;

    public User()
    {
    }

    public User(int newId, String email, String password, boolean isAdmin)
    {
        this.id = newId;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.money = 0;
    }

    public User(String email, String password, boolean isAdmin)
    {
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.money = 0;
    }

    public int getId()
    {
        return id;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }

    public boolean getIsAdmin()
    {
        return isAdmin;
    }

    public int getMoney()
    {
        return money;
    }

    public Set<Game> getGamesAsPlayer1()
    {
        return gamesAsPlayer1;
    }

    public Set<Game> getGamesAsPlayer2()
    {
        return gamesAsPlayer2;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setIsAdmin(boolean isAdmin)
    {
        this.isAdmin = isAdmin;
    }

    public void setMoney(int money)
    {
        this.money = money;
    }

    public void setGamesAsPlayer1(Set<Game> gamesAsPlayer1)
    {
        this.gamesAsPlayer1 = gamesAsPlayer1;
    }

    public void setGamesAsPlayer2(Set<Game> gamesAsPlayer2)
    {
        this.gamesAsPlayer2 = gamesAsPlayer2;
    }

    @Override
    public String toString()
    {
        return "User{" + "id=" + id + ", email=" + email + ", password=" + password + ", isAdmin=" + isAdmin + ", money=" + money + '}';
    }

    public void addTournament(Tournament t)
    {
        userTournaments.add(t);
        t.getParticipants().add(this);
    }

    public List<Tournament> getUserTournaments()
    {
        return userTournaments;
    }

    public void setUserTournaments(List<Tournament> userTournaments)
    {
        this.userTournaments = userTournaments;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (!User.class.isAssignableFrom(obj.getClass()))
        {
            return false;
        }
        final User other = (User) obj;
        if ((this.id == 0) ? (other.id != 0) : this.id != other.id)
        {
            return false;
        }
        return true;
    }

    public void addGameP1(Game g)
    {
        gamesAsPlayer1.add(g);
    }
    public void addGameP2(Game g)
    {
        gamesAsPlayer2.add(g);
    }

}
