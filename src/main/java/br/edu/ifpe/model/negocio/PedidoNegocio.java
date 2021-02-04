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
package br.edu.ifpe.model.negocio;

import br.edu.ifpe.model.classes.Pedido;
import br.edu.ifpe.model.dao.ItemPedidoDAO;
import br.edu.ifpe.model.dao.PedidoDAO;
import br.edu.ifpe.model.dao.resources.HibernateUtill;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Luciano
 */
public class PedidoNegocio {

    private static PedidoNegocio instance;
    private final HibernateUtill UTILL;

    PedidoDAO pedidoJDBC = PedidoDAO.getInstance();

    public PedidoNegocio() {
        UTILL = HibernateUtill.getInstance();
    }

    public static PedidoNegocio getInstance() {
        if (instance == null) {
            instance = new PedidoNegocio();
        }
        return instance;
    }

    public void inserirPedido(Pedido pedido) {
        pedidoJDBC.inserir(pedido);
        FacesMessage mensagem = new FacesMessage("Pedido efetuado com sucesso!");
        FacesContext contexto = FacesContext.getCurrentInstance();
        contexto.addMessage(null, mensagem);
    }

    public void alterarPedido(Pedido pedido) {
        pedidoJDBC.alterar(pedido);
    }

    public Pedido recuperarPedido(int codigo) {
        return pedidoJDBC.recuperar(codigo);
    }

    public void deletarPedido(Pedido pedido) {
        pedidoJDBC.deletar(pedido);
    }

    public List<Pedido> listarTodosPedidos() {
        return pedidoJDBC.listarTodos();
    }

    public Pedido recuperarPedido(String cod) {
        return this.recuperarPedido(cod);
    }

}
