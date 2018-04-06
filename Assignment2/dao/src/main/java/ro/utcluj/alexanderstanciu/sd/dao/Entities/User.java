/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.dao.Entities;

import java.util.Base64;

/**
 *
 * @author XtraSonic
 */
public class User {

    public static int UNSET_ID = -1;

    private int id = UNSET_ID;
    private String email;
    private String password;
    private boolean isAdmin = false;

    public User(int id, String email, String password, boolean isAdmin)
    {

        this.id = id;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;

    }

    public User(String email, String password, boolean isAdmin)
    {
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public final void setPassword(String password)
    {
        this.password = new String(Base64.getEncoder().encode(password.getBytes()));
    }

    private String getPlainPassword()
    {
        return password; 
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

    public void setId(int id)
    {
        if (this.id == UNSET_ID && id > 0)
        {
            this.id = id;
        }
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public boolean getIsAdmin()
    {
        return this.isAdmin;
    }

    public String getName()
    {
        return email;
    }
    
    @Override
    public String toString()
    {
        return "User{" + "id=" + id + ", email=" + email + ", password=" + password + ", isAdmin=" + isAdmin + '}';
    }

}
