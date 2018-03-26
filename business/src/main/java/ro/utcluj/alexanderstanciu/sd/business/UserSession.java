/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.business;

import java.util.Base64;
import ro.utcluj.alexanderstanciu.sd.business.Entities.User;
import ro.utcluj.alexanderstanciu.sd.business.Interfaces.UserGateway;
import ro.utcluj.alexanderstanciu.sd.business.Interfaces.LogInValidator;

/**
 *
 * @author XtraSonic
 */
public class UserSession implements LogInValidator{
    private UserGateway gateway;
    private User user;
    
    public UserSession(UserGateway gateway)
    {
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
    
    public User getUserById(int id)
    {
        return gateway.findById(id);
    }

    void createNewUser(String email, String password, boolean admin)
    {
        User u = new User(email, password, admin);
        u.setPassword(password);
        gateway.insert(u);
    }
    
}
