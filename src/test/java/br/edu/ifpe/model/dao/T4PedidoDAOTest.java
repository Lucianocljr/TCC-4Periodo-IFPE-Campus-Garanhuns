/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.model.dao;

import br.edu.ifpe.model.classes.Cliente;
import br.edu.ifpe.model.classes.Endereco;
import br.edu.ifpe.model.classes.ItemPedido;
import br.edu.ifpe.model.classes.Pedido;
import br.edu.ifpe.model.classes.Produto;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Luciano
 */
public class T4PedidoDAOTest {

    private static Cliente cliente;
    private static Endereco endereco;
    private static Pedido pedido;
    private static Produto p1;
    private static ItemPedido item;
    private static List<ItemPedido> itensPedidos;

    @BeforeClass
    public static void deveInserirItemPedido() {
        endereco = new Endereco("estado", "cidade", "cep", "bairro",
                "logradouro", 0, "complemento");

        cliente = new Cliente("nomeCliente", "senhaCliente", "cpfCliente",
                LocalDate.now(), "telefoneCliente", "emailCliente", endereco);
        ClienteDAO.getInstance().inserir(cliente);

        p1 = new Produto("nome", 1, 2);
        ProdutoDAO.getInstance().inserir(p1);

        itensPedidos = new ArrayList();
        item = new ItemPedido(p1, 10);
        ItemPedidoDAO.getInstance().inserir(item);

        itensPedidos.add(item);

        pedido = new Pedido(cliente, 10, "testando", itensPedidos);
        PedidoDAO.getInstance().inserir(pedido);
    }

    @Test
    public void deve1RecuperarPedido() {
        List<Pedido> pedidos = PedidoDAO.getInstance().listarTodos();
        assertEquals("deveRecuperarPedido", pedido,
                pedidos.get(pedidos.size() - 1));
    }

    @Test
    public void deve2AlterarPedido() {
        pedido.setStatus("Status alterado");

        PedidoDAO.getInstance().alterar(pedido);

        List<Pedido> pedidos = PedidoDAO.getInstance().listarTodos();
        assertEquals("deveAlterarPedido", pedido,
                pedidos.get(pedidos.size() - 1));
    }

    @Test
    public void deve3VerificarTodos() {
        List<Pedido> pedidos = PedidoDAO.getInstance().listarTodos();

        assertTrue(pedidos.contains(pedido));
    }

    @AfterClass()
    public static void deveDeletarTodos() {
        ClienteDAO.getInstance().deletar(cliente);
        ProdutoDAO.getInstance().deletar(p1);
        ItemPedidoDAO.getInstance().deletar(item);
        PedidoDAO.getInstance().deletar(pedido);
    }

}
