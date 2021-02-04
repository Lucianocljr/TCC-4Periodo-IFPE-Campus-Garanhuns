/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.controller.pedido;

import br.edu.ifpe.model.classes.Produto;
import br.ifpe.edu.util.DialogUtil;
import static br.ifpe.edu.util.PrimefacesSessionUtil.putObjectInSession;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Marcela
 */
@Named
@ViewScoped
public class SelecaoDeItem implements Serializable {

    public SelecaoDeItem() {
    }

    public void dialogSelecaoProduto() {

        Map<String, Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("modal", true);
        options.put("width", "800");
        options.put("height", "600");
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        PrimeFaces.current().dialog().openDynamic("dialogProdutos", options, null);

    }
    
    public void abrirDialogOpcoesDoItem(Produto produto) {        
        DialogUtil.openDialog("opcoesProduto");
        putObjectInSession("produto", produto);
    }
    

    public void selectProdFromDialog(SelectEvent event) {
        PrimeFaces.current().dialog().closeDynamic(event.getObject());
    }

}
