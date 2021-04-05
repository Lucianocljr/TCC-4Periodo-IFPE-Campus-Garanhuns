/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.model.dao;

import br.edu.ifpe.model.classes.ItemPedido;
import br.edu.ifpe.model.classes.Produto;
import java.util.List;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Ignore;

/**
 *
 * @author Luciano
 */
public class T3ItemPedidoDAOTest {

    private static ItemPedido item;
    private static Produto p1;

    @BeforeClass
    public static void deveInserirItemPedido() {
        p1 = new Produto("nome", 1, 2, false);
        ProdutoDAO.getInstance().inserir(p1);

        item = new ItemPedido(p1, 10);
        ItemPedidoDAO.getInstance().inserir(item);
    }

    @Test
    public void deve1RecuperarItem() {
        List<ItemPedido> itens = ItemPedidoDAO.getInstance().listarTodos();
        assertEquals("deveRecuperarItem", item,
                itens.get(itens.size() - 1));
    }
    
    @Test
    public void deve2AlterarItem() {
        item.setQuantidade(14);

        ItemPedidoDAO.getInstance().alterar(item);

        List<ItemPedido> itens = ItemPedidoDAO.getInstance().listarTodos();
        assertEquals("deveRecuperarItem", item,
                itens.get(itens.size() - 1));
    }

    @Test
    public void deve3VerificarTodos() {
        List<ItemPedido> itens = ItemPedidoDAO.getInstance().listarTodos();

        assertTrue(itens.contains(item));
    }

    @AfterClass()
    public static void deveDeletarCliente() {
        ProdutoDAO.getInstance().deletar(p1);
        
        ItemPedidoDAO.getInstance().deletar(item);
    }

}
