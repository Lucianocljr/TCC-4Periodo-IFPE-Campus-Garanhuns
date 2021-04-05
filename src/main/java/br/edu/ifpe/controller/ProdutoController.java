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
package br.edu.ifpe.controller;

import br.edu.ifpe.model.classes.Produto;
import br.edu.ifpe.model.negocio.ProdutoNegocio;
import java.io.Serializable;
import java.time.Clock;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Luciano
 * @author Marcela
 */
@SuppressWarnings("serial")
@Named(value = "produtoController")
@ViewScoped
public class ProdutoController implements Serializable {

    private ProdutoNegocio produtoModel = null;
    private Produto produto;
    private Produto selectedProduto;

    private List<Produto> produtosFiltrados;

    public ProdutoController() {
        this.produto = new Produto();
        this.produtoModel = new ProdutoNegocio();
        String codigo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        if (codigo != null) {
            this.selectedProduto = produtoModel.recuperarProduto(Integer.parseInt(codigo));
        }
    }

    public void inserirProdutoAction() {
//        produtoModel.inserirProduto(produto);
        this.produtoModel.inserirProduto(this.produto);
        //addMessage(FacesMessage.SEVERITY_INFO, "Produto Cadastrado com Sucesso!","Message Content");
        showProdCadSucesso();
        this.produto = new Produto();
    }

    public String alterarProdutoAction() {
        this.produtoModel.alterarProduto(this.selectedProduto);
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produto alterado com sucesso"));
         
        //addMessage(FacesMessage.SEVERITY_INFO, "Produto Alterado com Sucesso!","Message Content");
        showProdAltSucesso();
        return "listarProduto.xhtml?faces-redirect=true";
        
    }

    public List<Produto> getProdutosFiltrados() {
        return produtosFiltrados;
    }

    public void setProdutosFiltrados(List<Produto> produtosFiltrados) {
        this.produtosFiltrados = produtosFiltrados;
    }

    public Produto recuperarProdutoAction(int codigo) {
        return produtoModel.recuperarProduto(codigo);
    }

    public void deletarProdutoAction(Produto produto) {
        produtoModel.deletarProduto(produto);
        
        showProdDelSucesso();
        
    }
    
    public String inativarProdutoAction(Produto produto){
        this.produtoModel.inativarProduto(produto);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produto deletado com sucesso."));
        return "listarCliente.xhtml?faces-redirect=true";
    }

    public List<Produto> listarTodosProdutoAction() {
        System.out.println(produtoModel.listarTodosProdutos().get(0));
        return produtoModel.listarTodosProdutos();
    }
    
    public List<Produto> listarTodosProdutosAtivosAction() {
        return produtoModel.listarTodosProdutosAtivos();
    }

    public ProdutoNegocio getProdutoModel() {
        return produtoModel;
    }

    public void setProdutoModel(ProdutoNegocio produtoModel) {
        this.produtoModel = produtoModel;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Produto getSelectedProduto() {
        return selectedProduto;
    }

    public void setSelectedProduto(Produto selectedProduto) {
        this.selectedProduto = selectedProduto;
    }

    public String linkAlterar(Integer id) {
        return "alterarProduto.xhtml?faces-redirect=true&id=" + id;
    }

    public String linkVoltar() {
        return "home.xhtml?faces-redirect=true";
    }
    
    //Criado em 11/02/2021 - Luciano Júnior
    
    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public void showProdCadSucesso() {
        FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_INFO, "Produto Cadastrado com Sucesso!", "Message Content"));
    }
    public void showProdAltSucesso() {
        FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_INFO, "Produto Alterado com Sucesso!", "Message Content"));
    }
    public void showProdDelSucesso() {
        FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_INFO, "Produto Deletado com Sucesso!", "Message Content"));
    }

}
