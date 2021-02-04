
import br.edu.ifpe.controller.pedido.PedidoController;
import br.edu.ifpe.model.classes.Pedido;
import br.edu.ifpe.model.classes.Produto;
import br.edu.ifpe.model.classes.ItemPedido;
import br.edu.ifpe.model.dao.ClienteDAO;
import br.edu.ifpe.model.dao.PedidoDAO;
import br.edu.ifpe.model.dao.ProdutoDAO;
import java.util.ArrayList;
import java.util.List;

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
/**
 *
 * @author Luciano
 */
public class teste {

    public static void main(String[] args) {
        
//       Endereco endereco = new Endereco("1", "1", "1", "1",
//                "1", 0, "1");
//        EnderecoDAO.getInstance().inserir(endereco);
//        
//        Cliente cliente = new Cliente("1", "teste", "1", 
//                LocalDate.now(), "1", "t", endereco);
//        ClienteDAO.getInstance().inserir(cliente);

                
                List<ItemPedido> pe = new ArrayList();
                
                ItemPedido e = new ItemPedido(ProdutoDAO.getInstance().recuperar(2), 5);
                pe.add(e);
        
        Pedido  p = new Pedido(ClienteDAO.getInstance().recuperar(1), 3,"status",pe);
        
        PedidoController ppp = new PedidoController();
        
        
        
        p.setStatus("t");
        
        PedidoDAO.getInstance().alterar(p);
        
        
    }
}
