package br.edu.ifpe.controller.pedido;

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
import br.edu.ifpe.model.classes.Cliente;
import br.edu.ifpe.model.classes.ItemPedido;
import br.edu.ifpe.model.classes.Pedido;
import br.edu.ifpe.model.dao.ItemPedidoDAO;
import br.edu.ifpe.model.negocio.PedidoNegocio;
import static br.ifpe.edu.util.FacesMessageUtil.infoMessage;
import static br.ifpe.edu.util.FacesMessageUtil.errorMessage;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.hibernate.internal.util.SerializationHelper;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Luciano
 */
@Named("pedidoBean")
@ViewScoped
public class PedidoController implements Serializable {

    private Pedido pedido;
    private PedidoNegocio pedidoModel;

    private List<Pedido> pedidos;

    private Pedido pedidoSelecionado;

    private String status = "";

    public PedidoController() {

    }

    @PostConstruct
    public void init() {
        this.pedido = new Pedido();
        this.pedidoModel = new PedidoNegocio();

        String codigo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        if (codigo != null) {
            this.pedidoSelecionado = pedidoModel.recuperarPedido(Integer.parseInt(codigo));
        }

    }

    public void produtoSelecionado(SelectEvent event) {
        ItemPedido item = (ItemPedido) event.getObject();
        this.pedido.adicionarItem(item);
    }

    public void clienteSelecionado(SelectEvent event) {
        Cliente cliente = (Cliente) event.getObject();
        this.pedido.setCliente(cliente);
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public void inserirPedido() {
        try {
            if(!ItemPedidoDAO.getInstance().verificarQuantidade(this.pedido.getItensPedido())){
                
            }
            ItemPedidoDAO.getInstance().diminuirQuantidade(this.pedido.getItensPedido());
            this.pedido.setStatus("Aprovado");
            pedidoModel.inserirPedido(this.pedido);

            infoMessage("Pedido Realizado Com Sucesso");
        } catch (Exception e) {
            errorMessage("Não foi Possivel Finalizar o Pedido" + e.toString());
        }

    }

    public String detelharPedido(int id) {
        return "detalhepedido.xhtml?faces-redirect=true&id=" + id;
    }

    public void mostrarStatus() {

    }

    public void alterarPedido(Pedido pedido) {
        pedidoModel.alterarPedido(pedido);
    }

    public Pedido recuperarPedido(int codigo) {
        return pedidoModel.recuperarPedido(codigo);
    }

    public void deletarPedido(Pedido pedido) {
        pedidoModel.deletarPedido(pedido);
    }

    public List<Pedido> listarTodosPedidos() {
        this.pedidos = pedidoModel.listarTodosPedidos();
        return this.pedidos;
    }

    public Pedido recuperarPedido(String cod) {
        return this.recuperarPedido(cod);
    }

    public Pedido getPedidoSelecionado() {
        return pedidoSelecionado;
    }

    public void setPedidoSelecionado(Pedido pedidoSelecionado) {
        this.pedidoSelecionado = pedidoSelecionado;
    }

    public void formChange() {
        System.err.println("listener funcionando via ajax");
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String alterarStatus() {
        this.pedidoSelecionado.setStatus(this.status);
        this.pedidoModel.alterarPedido(this.pedidoSelecionado);

        FacesMessage mensagem = new FacesMessage("Status do pedido alterado!");
        FacesContext contexto = FacesContext.getCurrentInstance();
        contexto.addMessage(null, mensagem);

        return "listaPedidos.xhtml?faces-redirect=true";
    }
}
