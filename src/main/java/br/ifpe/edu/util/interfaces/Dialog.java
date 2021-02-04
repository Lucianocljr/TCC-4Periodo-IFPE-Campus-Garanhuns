/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpe.edu.util.interfaces;

import java.util.Map;

/**
 *
 * @author Marcela
 */
public interface Dialog{
    
    public void abrirDialog(Map<String, Object> opcoes, String dialog);
    
    public void fecharDialog(Object objeto);
    
}
