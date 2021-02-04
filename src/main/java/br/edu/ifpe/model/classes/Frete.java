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
package br.edu.ifpe.model.classes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Luciano Júnior <lucianocljr7@gmail.com>
 */

@Entity
public class Frete {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Column(name = "valorFrete", length = 6, nullable = false)
    private double valorFrete;
    
    @Column(name = "dataHoraFrete", length = 4, nullable = false)
    private LocalDateTime dataHoraFrete;
    
    @OneToOne
    @JoinColumn(name = "cod_endereco")
    private Endereco endereco;
    
    @OneToMany
    @JoinColumn(name = "cod_produto")
    private List<Produto> produtos;

    public Frete() {
    }

    public Frete(int id, double valorFrete, LocalDateTime dataHoraFrete, 
                        Endereco endereco, List<Produto> produtos) {
        this.id = id;
        this.valorFrete = valorFrete;
        this.dataHoraFrete = dataHoraFrete;
        this.endereco = endereco;
        this.produtos = produtos;
    }

    public int getId() {
        return id;
    }

    public double getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(double valorFrete) {
        this.valorFrete = valorFrete;
    }

    public LocalDateTime getDataHoraFrete() {
        return dataHoraFrete;
    }

    public void setDataHoraFrete(LocalDateTime dataHoraFrete) {
        this.dataHoraFrete = dataHoraFrete;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public String toString() {
        return "Frete{" + "valorFrete=" + valorFrete + ", dataHoraFrete=" + 
                dataHoraFrete + ", endereco=" + endereco + ", produtos=" + 
                produtos + '}';
    }

    @Override
    public int hashCode() {
        final int PRIMO = 5;
        int resultado = 1;
        resultado += (resultado * PRIMO) + id;
        resultado += (resultado * PRIMO) + (int) valorFrete;
        resultado += (resultado * PRIMO) + dataHoraFrete.hashCode();
        resultado += (resultado * PRIMO) + endereco.hashCode();
        
        return (resultado * PRIMO) + produtos.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
       if(!(obj instanceof Frete))
            return false;

        if (((Frete) obj).valorFrete != this.valorFrete)
            return false;

         if (!((Frete) obj).dataHoraFrete.equals(this.dataHoraFrete))
            return false;

         if (!((Frete) obj).endereco.equals(this.endereco))
            return false;

        return ((Frete) obj).produtos.equals(this.produtos);
    } 
}
