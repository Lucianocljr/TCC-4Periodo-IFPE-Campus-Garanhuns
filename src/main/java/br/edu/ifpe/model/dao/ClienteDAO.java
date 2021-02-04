/*MIT License

Copyright (c) 2020 Lucas Mendes,Marcela Cardoso,Luciano Jr.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.*/
package br.edu.ifpe.model.dao;

import br.edu.ifpe.model.dao.resources.HibernateUtill;
import br.edu.ifpe.model.classes.Cliente;
import br.edu.ifpe.model.dao.interfaces.ClienteInterfaceDAO;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Luciano Júnior <lucianocljr7@gmail.com>
 */
public class ClienteDAO implements ClienteInterfaceDAO {

    private final HibernateUtill UTILL;
    private static ClienteDAO instance;
    private Session session;

    public ClienteDAO() {
        UTILL = HibernateUtill.getInstance();
    }

    public static ClienteDAO getInstance() {
        if (instance == null) {
            instance = new ClienteDAO();
        }
        return instance;
    }

    @Override
    public void inserir(Cliente cliente) {
        session = UTILL.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(cliente);
            transaction.commit();
        } catch (Exception createClienteException) {
            System.out.println(createClienteException.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public Cliente recuperar(Integer codigo) {
        try {
            session = UTILL.getSession();
            return (Cliente) session.createQuery(
                    "FROM Cliente where id=" + codigo).getSingleResult();
        } catch (Exception readClienteException) {
            System.out.println(readClienteException.getMessage());
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public void alterar(Cliente cliente) {
        session = UTILL.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(cliente);
            transaction.commit();
        } catch (Exception updateClienteException) {
            System.out.println(updateClienteException.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void deletar(Cliente cliente) {
        session = UTILL.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(cliente);
            transaction.commit();
        } catch (Exception delClienteException) {
            System.out.println(delClienteException.getMessage());
            if(transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public List<Cliente> listarTodos() {
        session = UTILL.getSession();
        List<Cliente> clientes = null;
        try {
            clientes = (List) session.createQuery
                                ("FROM Cliente").getResultList();
        } catch (Exception readAllClientesException) {
            System.out.println(readAllClientesException.getMessage());
        } finally {
            session.close();
        }
        return clientes;
      
    }
    
}
