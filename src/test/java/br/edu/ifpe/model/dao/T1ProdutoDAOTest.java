///*MIT License
//
//Copyright (c) 2020 Lucas Mendes,Marcela Cardoso,Luciano Jr.
//
//Permission is hereby granted, free of charge, to any person obtaining a copy
//of this software and associated documentation files (the "Software"), to deal
//in the Software without restriction, including without limitation the rights
//to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//copies of the Software, and to permit persons to whom the Software is
//furnished to do so, subject to the following conditions:
//
//The above copyright notice and this permission notice shall be included in all
//copies or substantial portions of the Software.
//
//THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//SOFTWARE.*/
package br.edu.ifpe.model.dao;

import br.edu.ifpe.model.classes.Produto;
import java.util.List;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Ignore;

/**
 *
 * @author Lucas Mendes <lucas.mendes147@live.com>
 */
public class T1ProdutoDAOTest {

    private static Produto produto;

    @BeforeClass
    public static void deveInserirProdutos() {
        produto = new Produto("nome000", 3, 4);
        ProdutoDAO.getInstance().inserir(produto);

    }

    @Test
    public void deve1RecuperarProduto() {
        List<Produto> produtos = ProdutoDAO.getInstance().listarTodos();
        assertEquals("deveRecuperarProduto", produto,
                produtos.get(produtos.size() - 1));
    }

    @Test
    public void deve2AlterarProduto() {
        produto.setNomeProduto("nomeAlterado");
        produto.setQuantProduto(1212);

        ProdutoDAO.getInstance().alterar(produto);
        List<Produto> produtos = ProdutoDAO.getInstance().listarTodos();
        assertEquals("deveAlterarProduto", produto,
                produtos.get(produtos.size() - 1));
    }

    @Test
    public void deve3RecuperarTodosProdutos() {
        List<Produto> produtos = ProdutoDAO.getInstance().listarTodos();
        assertTrue(produtos.contains(produto));
    }

    @AfterClass()
    public static void deveDeletarProdutos() {
        ProdutoDAO.getInstance().deletar(produto);
    }

}
