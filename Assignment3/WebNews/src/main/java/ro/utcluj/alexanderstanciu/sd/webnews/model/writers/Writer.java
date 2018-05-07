/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.webnews.model.writers;

import java.util.Base64;

/**
 *
 * @author XtraSonic
 */
public class Writer {
    private String username;
    private String password;

    public Writer(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString()
    {
        return "Writer{" + "username=" + username + ", password=" + Base64.getEncoder().encodeToString(password.getBytes()) + '}';
    }

    @Override
    public boolean equals(Object o)
    {
        
        // If the object is compared with itself then return true  
        if (o == this) {
            return true;
        }
 
        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Writer)) {
            return false;
        }
         
        // typecast o to Complex so that we can compare data members 
        Writer c = (Writer) o;
         
        // Compare the data members and return accordingly 
        return c.getPassword().equals(this.getPassword()) &&
               c.getUsername().equals(this.getUsername());
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public void update(Writer w)
    {
        username = w.getUsername();
        password = w.getPassword();
    }
    
    
}
