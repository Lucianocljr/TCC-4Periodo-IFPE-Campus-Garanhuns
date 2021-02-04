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

import br.edu.ifpe.model.classes.Pagamento;
import br.edu.ifpe.model.dao.interfaces.PagamentoInterfaceDAO;
import br.edu.ifpe.model.dao.resources.HibernateUtill;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Luciano
 */
public class PagamentoDAO implements PagamentoInterfaceDAO {

    private final HibernateUtill UTILL;
    private static PagamentoDAO instance;
    private Session session;

    public PagamentoDAO() {
        UTILL = HibernateUtill.getInstance();
    }

    public static PagamentoDAO getInstance() {
        if (instance == null) {
            instance = new PagamentoDAO();
        }
        return instance;
    }

    @Override
    public void inserir(Pagamento pagamento) {
        session = UTILL.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(pagamento);
            transaction.commit();
        } catch (Exception createPagamentoException) {
            System.out.println(createPagamentoException.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public Pagamento recuperar(Integer codigo) {
        try {
            session = UTILL.getSession();
            return (Pagamento) session.createQuery(
                    "FROM Pagamento where id=" + codigo).getSingleResult();
        } catch (Exception readPagamentoException) {
            System.out.println(readPagamentoException.getMessage());
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public void alterar(Pagamento pagamento) {
        session = UTILL.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(pagamento);
            transaction.commit();
        } catch (Exception updatePagamentoException) {
            System.out.println(updatePagamentoException.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void deletar(Pagamento pagamento) {
        session = UTILL.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(pagamento);
            transaction.commit();
        } catch (Exception delPagamentoException) {
            System.out.println(delPagamentoException.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Pagamento> listarTodos() {
        session = UTILL.getSession();
        List<Pagamento> pagamentos = null;
        try {
            pagamentos = (List) session.createQuery("FROM Pagamento").getResultList();
        } catch (Exception readAllPagamentosException) {
            System.out.println(readAllPagamentosException.getMessage());
        } finally {
            session.close();
            return pagamentos;
        }
    }
}
