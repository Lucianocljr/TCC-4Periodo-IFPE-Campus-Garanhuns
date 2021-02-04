/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.model.dao;

import br.edu.ifpe.model.classes.Usuario;
import br.edu.ifpe.model.dao.interfaces.UsuarioInterfaceDAO;
import br.edu.ifpe.model.dao.resources.HibernateUtill;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author fernando
 */
public class UsuarioDao implements UsuarioInterfaceDAO {

    Session session = HibernateUtill.getInstance().getSession();

    @Override
    public void inserir(Usuario d) {

        Transaction transaction = session.beginTransaction();

        try {
            session.save(d);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new Error("Não foi possivel salvar");
        } finally {
            session.close();
        }
    }

    @Override
    public void alterar(Usuario d) {
        Transaction transaction = session.beginTransaction();
        try {
            session.update(d);
            transaction.commit();
        } catch (Exception updateProdutoException) {
            transaction.rollback();
            throw new Error("Não foi possivel alterar");
        } finally {
            session.close();
        }
    }

    @Override
    public Usuario recuperar(Integer d) {
        try {
            
            return (Usuario) session.createQuery(
                    "FROM Usuario where id=" + d).getSingleResult();
        } catch (Exception readProdutoException) {            
            return null;
        } finally {
            session.close();
        }
    }
    
    public Usuario buscarPorNomeSenha(String nome, String senha) {
        List<Usuario> usuarios = this.listarTodos();

        for(Usuario usuario : usuarios){
            if(usuario.getNome().equals(nome) &&
                    usuario.getSenha().equals(senha)){
                return usuario;
            }
        }
        
        return null;
    }

    @Override
    public void deletar(Usuario d) {
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(d);
            transaction.commit();
        } catch (Exception delProdutoException) {            
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Usuario> listarTodos() {
        List<Usuario> usuarios = null;
        try {
            usuarios = (List) session.createQuery("FROM Usuario").getResultList();
        } catch (Exception readAllProdutosException) {
            System.out.println(readAllProdutosException.getMessage());
        } finally {
            session.close();
            return usuarios;
        }
    }

}
