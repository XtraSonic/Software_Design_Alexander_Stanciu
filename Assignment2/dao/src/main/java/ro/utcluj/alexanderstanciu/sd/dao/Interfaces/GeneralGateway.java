/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.dao.Interfaces;

import java.util.List;

/**
 *
 * @author XtraSonic
 */
public interface GeneralGateway<T> {

    public int insert(T object);

    public void update(T object);

    public void delete(T object);

    public T findById(int id);

    public List<T> findAll();
    
}
