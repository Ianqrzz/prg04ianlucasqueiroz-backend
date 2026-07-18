package br.com.ifba.itempedido.entity;

import br.com.ifba.infraestructure.entity.PersistenceEntity;
import br.com.ifba.pedido.entity.Pedido;
import br.com.ifba.produto.entity.Produto;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemPedido extends PersistenceEntity {
// Items que compõem um pedido, cada item pode ter varios de um produto, o total de varios
// itens pedidos formam um pedido


    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    private Integer quantidade;

    private BigDecimal valorUnitario;

    private BigDecimal valorTotal;

}
