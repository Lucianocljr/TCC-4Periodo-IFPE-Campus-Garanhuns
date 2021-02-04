/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpe.edu.util;

import java.util.Map;
import org.primefaces.context.PrimeFacesContext;

/**
 *
 * @author Lucas
 */
public abstract class PrimefacesSessionUtil {  
    
    private static Map<String, Object> obtainSessionMap(){
        return PrimeFacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    }

    public static void putObjectInSession(String key, Object ob) {
        obtainSessionMap().put(key, ob);
    }
    
    public static Object catchObjectFromSession(String key){
        return obtainSessionMap().get(key);        
    }
    
    public static void removeObjectFromSession(String key){
        obtainSessionMap().remove(key);        
    }
    
    public boolean verifyIfAttributeExistsInSession(String atribute){
        return obtainSessionMap().containsKey(atribute);
    }
    
}
