/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.dao.Entities;

import java.time.LocalDate;

/**
 *
 * @author XtraSonic
 */
public class Tournament {

    public static int UNSET_ID = -1;

    private int id = UNSET_ID;
    private String name;
    private LocalDate date;
    private int prize_pool;

    public Tournament(int id, String name, LocalDate date, int prize_pool)
    {
        this.id = id;
        this.name = name;
        this.date = date;
        this.prize_pool = prize_pool;
    }

    public Tournament(String name, LocalDate date, int prize_pool)
    {
        this.name = name;
        this.date = date;
        this.prize_pool = prize_pool;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public LocalDate getDate()
    {
        return date;
    }

    public int getPrize_pool()
    {
        return prize_pool;
    }
    
    
}
