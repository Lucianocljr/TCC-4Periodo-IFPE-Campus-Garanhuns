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

import br.edu.ifpe.model.classes.Produto;
import br.edu.ifpe.model.dao.ProdutoDAO;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Luciano
 */
public class ProdutoNegocio implements Serializable {

    private static ProdutoNegocio instance;

    public static ProdutoNegocio getInstance() {
        if (instance == null) {
            instance = new ProdutoNegocio();
        }
        return instance;
    }

    ProdutoDAO produtoJDBC = new ProdutoDAO();

    public void inserirProduto(Produto produto) {
        if (produto.getQuantProduto() < 0) {
            FacesMessage mensagem = new FacesMessage
                        ("A quantidade do produto deve ser maior que zero!");
            FacesContext contexto = FacesContext.getCurrentInstance();
            contexto.addMessage(null, mensagem);
        }else{
            produtoJDBC.inserir(produto);
        } 
    }

    public void alterarProduto(Produto produto) {
        produtoJDBC.alterar(produto);
    }

    public Produto recuperarProduto(int codigo) {
        return produtoJDBC.recuperar(codigo);
    }

    public void deletarProduto(Produto produto) {
        produtoJDBC.deletar(produto);
    }

    public List<Produto> listarTodosProdutos() {
        return produtoJDBC.listarTodos();
    }

}
