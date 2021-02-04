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

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Luciano
 */
@Entity
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int codPedido;

    @OneToOne
    @JoinColumn(name = "cod_cliente")
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<ItemPedido> itensPedido = new ArrayList<>();

    @Column(name = "valorFretePedido", length = 10, nullable = false)
    private double valorFretePedido;

    @Column(name = "valorTotalDoPedido", length = 10, nullable = false)
    private double totalDoPedido = this.totalDoPedido();

    @Column(name = "dataHoraPedido", length = 30, nullable = true)
    private LocalDateTime dataHoraPedido = LocalDateTime.now();
    
    @Column(name = "status", nullable = true)
    private String status;

    public Pedido() {

    }

    public Pedido(Cliente cliente, double valorFretePedido, String status, List<ItemPedido> itens) {
        this.cliente = cliente;
        this.valorFretePedido = valorFretePedido;
        this.status = status;
        this.itensPedido = itens;
    }
    
    
    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }

    public void adicionarItem(ItemPedido item) {
        this.itensPedido.add(item);
    }

    public void removerItem(ItemPedido item) {
        boolean remove = this.itensPedido.remove(item);
    }

    public double valorTotalItens() {

        if (getItensPedido().isEmpty()) {
            return 0.0;
        }

        return getItensPedido().stream()
                .map((item) -> item.total())
                .reduce((ac, item) -> ac + item).get();

    }

    public double frete() {

        if (this.valorTotalItens() <= 20.0) {
            this.valorFretePedido = 2.0;
            return this.valorFretePedido;
        } else {
            this.valorFretePedido = 0.0;
            return this.valorFretePedido;
        }

    }

    public double totalDoPedido() {
        this.totalDoPedido = this.frete() + this.valorTotalItens();
        return this.totalDoPedido;
    }

    public List<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getValorFretePedido() {
        return valorFretePedido;
    }

    public void setValorFretePedido(double valorFretePedido) {
        this.valorFretePedido = valorFretePedido;
    }

    public double getTotalDoPedido() {
        return totalDoPedido;
    }

    public void setTotalDoPedido(double totalDoPedido) {
        this.totalDoPedido = totalDoPedido;
    }

    public int getCodPedido() {
        return codPedido;
    }

    public LocalDateTime getDataHoraPedido() {
        return dataHoraPedido;
    }

    public void setDataHoraPedido(LocalDateTime dataHoraPedido) {
        this.dataHoraPedido = dataHoraPedido;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pedido other = (Pedido) obj;
        if (Double.doubleToLongBits(this.valorFretePedido) != Double.doubleToLongBits(other.valorFretePedido)) {
            return false;
        }
        if (Double.doubleToLongBits(this.totalDoPedido) != Double.doubleToLongBits(other.totalDoPedido)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pedido{" + "codPedido=" + codPedido + ", cliente=" + cliente + ", itensPedido=" + itensPedido + ", valorFretePedido=" + valorFretePedido + ", totalDoPedido=" + totalDoPedido + ", dataHoraPedido=" + dataHoraPedido + ", status=" + status + '}';
    }

    
}
