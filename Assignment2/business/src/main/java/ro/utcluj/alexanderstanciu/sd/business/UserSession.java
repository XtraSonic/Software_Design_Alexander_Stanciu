/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.business;

import java.util.Base64;
import java.util.Observable;
import ro.utcluj.alexanderstanciu.sd.dao.Entities.User;
import ro.utcluj.alexanderstanciu.sd.dao.Interfaces.UserGateway;
import ro.utcluj.alexanderstanciu.sd.dao.Interfaces.LogInValidator;

/**
 *
 * @author XtraSonic
 */
public class UserSession extends Observable implements LogInValidator{
    private UserGateway gateway;
    private User user;
    
    public UserSession(UserGateway gateway)
    {
        super();
        this.gateway = gateway;
    }
    
    @Override
    public boolean logIn(String email, String password)
    {
        User u = gateway.findByEmail(email);
        if(u == null)
        {
            return false;
        }
        String decryptedPassword = this.decodePassword(u.getPassword());
        if(decryptedPassword.equals(password))
        {
            this.user= u;
            setChanged();
            return true;
        }
        return false;
    }

    public User getUser()
    {
        return user;
    }
    
    private String decodePassword(String s)
    {
        return new String(Base64.getDecoder().decode(s.getBytes()));
    }
    
    private String encodePassword(String s)
    {
        return new String(Base64.getEncoder().encode(s.getBytes()));
    }
    
    public User getUserById(int id)
    {
        return gateway.findById(id);
    }

    public void createNewUser(String email, String password, boolean admin)
    {
        User u = new User(email, password, admin);
        u.setPassword(encodePassword(password));
        gateway.insert(u);
    }
    
    public String getUserNameById(int id)
    {
        User u =getUserById(id);
        return u.getEmail();
    }
    
}
