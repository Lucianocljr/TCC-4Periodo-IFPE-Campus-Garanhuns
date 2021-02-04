/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.controller.login;

import br.edu.ifpe.model.classes.Usuario;
import br.edu.ifpe.model.dao.UsuarioDao;
import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Luciano
 */
@Named
@SessionScoped
public class Login implements Serializable {

    private Usuario usuario = new Usuario();

    private Usuario usuarioLogado;

    public String logar() throws Exception {

        usuarioLogado = buscaUsuario(this.usuario.getNome(), this.usuario.getSenha());

        if (usuarioLogado != null) {
            FacesContext.getCurrentInstance().
                    getExternalContext().getSessionMap().
                    put("usuarioLogado", this.usuarioLogado);
            return "/system/home.xhtml?faces-redirect=true";
        } else {
            return "login.xhtml?faces-redirect=true";
        }
    }

    public String logout() {
        return "../login.xhtml?faces-redirect=true";
    }

    private Usuario buscaUsuario(String nome, String senha) {
        UsuarioDao uDao = new UsuarioDao();

        return uDao.buscarPorNomeSenha(nome, senha);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

}
