/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpe.edu.util.interfaces;

/**
 *
 * @author Marcela
 */
public interface Session {
    
    public void putObjectInSession(String name, Object ob);
    public Object catchObjectFromSession(String name);
    public void removeObjectFromSession(Object bo);
    public boolean verifyIfAttributeExistsInSession(String atribute);
    
}
