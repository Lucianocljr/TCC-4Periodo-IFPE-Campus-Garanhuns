/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.controller;

import br.edu.ifpe.model.classes.Cliente;
import br.ifpe.edu.util.DialogUtil;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Lucas
 */
@Named
@ViewScoped
public class SelecionarCliente implements Serializable{
   
    public void abrirDialogSelecaoDeCliente() {        
        DialogUtil.openDialog("/dialogs/dialogSelecionarCliente");
    }
    
    public void retornarCliente(Cliente cli){
        DialogUtil.closeDialogAndReturn(cli);
    }
    
}
