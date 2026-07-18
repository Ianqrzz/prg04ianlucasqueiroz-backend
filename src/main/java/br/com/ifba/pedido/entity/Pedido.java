package br.com.ifba.pedido.entity;


import br.com.ifba.endereco.entity.Endereco;
import br.com.ifba.infraestructure.entity.PersistenceEntity;
import br.com.ifba.itempedido.entity.ItemPedido;
import br.com.ifba.usuario.entity.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pedido extends PersistenceEntity {

    //relacionamentos:
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itensPedido;

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;


    // Pagamento pagamento;


    private String numeroPedido;

    private BigDecimal valorTotal;

    private StatusPedido status;

    private String enderecoEntrega;

}
