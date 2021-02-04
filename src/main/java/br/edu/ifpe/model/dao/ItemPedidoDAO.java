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

import br.edu.ifpe.model.classes.ItemPedido;
import br.edu.ifpe.model.classes.Produto;
import br.edu.ifpe.model.dao.interfaces.ItemPedidoInterfaceDAO;
import br.edu.ifpe.model.dao.resources.HibernateUtill;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.TypedQuery;
import org.hibernate.Session;

/**
 *
 * @author Luciano
 */
public class ItemPedidoDAO implements ItemPedidoInterfaceDAO {

    private final HibernateUtill UTILL;
    private static ItemPedidoDAO instance;

    private ItemPedidoDAO() {
        UTILL = HibernateUtill.getInstance();
    }

    public static ItemPedidoDAO getInstance() {
        if (instance == null) {
            instance = new ItemPedidoDAO();
        }
        return instance;
    }

    @Override
    public void inserir(ItemPedido itemPedido) {
        Session session = UTILL.getSession();
        try {
            session.getTransaction().begin();
            session.save(itemPedido);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Erro ao INSERIR " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public void alterar(ItemPedido itemPedido) {
        Session session = UTILL.getSession();
        try {
            session.getTransaction().begin();
            session.update(itemPedido);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println("Erro ao ATUALIZAR " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public ItemPedido recuperar(Integer codigo) {
        ItemPedido itemPedido = null;

        Session session = UTILL.getSession();
        try {
            itemPedido = session.find(ItemPedido.class, codigo);
            session.close();
        } catch (Exception e) {
            System.out.println("Erro ao RECUPERAR " + e.toString());
        }
        return itemPedido;
    }

    @Override
    public void deletar(ItemPedido itemPedido) {
        Session session = UTILL.getSession();

        try {
            session.getTransaction().begin();
            session.delete(itemPedido);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao DELETAR " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public List<ItemPedido> listarTodos() {
        List<ItemPedido> itensPedido;

        try (Session session = UTILL.getSession()) {

            TypedQuery<ItemPedido> c = session.createNativeQuery("select * from itemPedido", ItemPedido.class);
            itensPedido = c.getResultList();
            if (itensPedido != null) {
                return itensPedido;
            }

        } catch (Exception e) {
            System.err.println("Erro ao recuperar todos" + e.toString());
        }
        return null;
    }

    public void diminuirQuantidade(List<ItemPedido> itens) {
        for (ItemPedido i : itens) {
            Produto p = i.getProduto();
            p.setQuantProduto((int) (p.getQuantProduto() - i.getQuantidade()));
            ProdutoDAO.getInstance().alterar(p);
        }
    }

    public boolean verificarQuantidade(List<ItemPedido> itens) {

        for (ItemPedido i : itens) {
            Produto p = ProdutoDAO.getInstance().recuperar(i.getProduto().getId());
            if (p.getQuantProduto() - i.getQuantidade() < 0) {
                FacesMessage mensagem = new FacesMessage("A quantidade do produto deve ser menor ou igual ao estoque!");
                FacesContext contexto = FacesContext.getCurrentInstance();
                contexto.addMessage(null, mensagem);
                return false;
            }
        }
        return true;
    }
}
