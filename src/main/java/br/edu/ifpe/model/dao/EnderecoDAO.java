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

import br.edu.ifpe.model.classes.Endereco;
import br.edu.ifpe.model.dao.interfaces.EnderecoInterfaceDAO;
import br.edu.ifpe.model.dao.resources.HibernateUtill;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Luciano Júnior <lucianocljr7@gmail.com>
 */
public class EnderecoDAO implements EnderecoInterfaceDAO {

    private final HibernateUtill UTILL;
    private static EnderecoDAO instance;
    private Session session;

    public EnderecoDAO() {
        UTILL = HibernateUtill.getInstance();
    }

    public static EnderecoDAO getInstance() {
        if (instance == null) {
            instance = new EnderecoDAO();
        }
        return instance;
    }

    @Override
    public void inserir(Endereco endereco) {
        session = UTILL.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(endereco);
            transaction.commit();
        } catch (Exception createEnderecoException) {
            System.out.println(createEnderecoException.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public Endereco recuperar(Integer codigo) {
        try {
            session = UTILL.getSession();
            return (Endereco) session.createQuery(
                    "FROM Endereco where id=" + codigo).getSingleResult();
        } catch (Exception readEnderecoException) {
            System.out.println(readEnderecoException.getMessage());
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public void alterar(Endereco endereco) {
        session = UTILL.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(endereco);
            transaction.commit();
        } catch (Exception updateEnderecoException) {
            System.out.println(updateEnderecoException.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void deletar(Endereco endereco) {
        session = UTILL.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(endereco);
            transaction.commit();
        } catch (Exception delEnderecoException) {
            System.out.println(delEnderecoException.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Endereco> listarTodos() {
        session = UTILL.getSession();
        List<Endereco> enderecos = null;
        try {
            enderecos = (List) session.createQuery
                                            ("FROM Endereco").getResultList();
        } catch (Exception readAllEnderecosException) {
            System.out.println(readAllEnderecosException.getMessage());
        } finally {
            session.close();
            return enderecos;
        }
    }
}
