/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.controller.pedido;

import static br.ifpe.edu.util.PrimefacesSessionUtil.catchObjectFromSession;
import static br.ifpe.edu.util.PrimefacesSessionUtil.removeObjectFromSession;

import br.edu.ifpe.model.classes.ItemPedido;
import br.edu.ifpe.model.classes.Produto;
import br.edu.ifpe.model.dao.ItemPedidoDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Marcela
 */
@Named
@ViewScoped
public class OpcoesItemSelecionado implements Serializable {

    private ItemPedido itemPedido = new ItemPedido();

    @PostConstruct
    public void init() {

        try {
            this.itemPedido.setProduto((Produto) catchObjectFromSession("produto"));
        } catch (Exception e) {
            throw new AbstractMethodError("Ocorreu um erro ao processar o pedido");
        } finally {
            removeObjectFromSession("produto");
        }

    }

   public void quantidade() {
        List<ItemPedido> itens = new ArrayList();
        itens.add(itemPedido);
        if (!ItemPedidoDAO.getInstance().verificarQuantidade(itens)) {
            FacesMessage mensagem = new FacesMessage("A quantidade do produto deve ser menor ou igual ao estoque!");
            FacesContext contexto = FacesContext.getCurrentInstance();
            contexto.addMessage(null, mensagem);
        } else {
            itemPedido.setQuantidade(itemPedido.getQuantidade());
        }
    }

    public ItemPedido getItemPedido() {
        return itemPedido;
    }

    public void setItemPedido(ItemPedido itemPedido) {
        this.itemPedido = itemPedido;
    }

    public void retorno() {
        System.out.println(this.itemPedido.getProduto());
        PrimeFaces.current().dialog().closeDynamic(this.itemPedido);
    }

}
