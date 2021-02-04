/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.controller;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Lucas
 */
@Named
@ViewScoped
public class Rotas implements Serializable {

    public String linkHome() {
        return "home.xhtml?faces-redirect=true";
    }

    public String linkLogin() {
        return "login.xhtml?faces-redirect=true";
    }
    
    public String linkCadastrarUsuario() {
        return "cadastrousuario.xhtml?faces-redirect=true";
    }

    public String linkAlterarCliente(Integer id) {
        return "alterarCliente.xhtml?faces-redirect=true&id=" + id;
    }

    public String linkListarCliente() {
        return "listarCliente.xhtml?faces-redirect=true";
    }

    public String linkCadastroCliente() {
        return "cadastroCliente.xhtml?faces-redirect=true";
    }

    public String linkCadastroProduto() {
        return "cadastroProduto.xhtml?faces-redirect=true";
    }

    public String linkListaProduto() {
        return "listarProduto.xhtml?faces-redirect=true";
    }

    public String linkListaPedidos() {
        return "listaPedidos.xhtml?faces-redirect=true";
    }

    public String linkNovoPedido() {
        return "pedido.xhtml?faces-redirect=true";
    }

}
