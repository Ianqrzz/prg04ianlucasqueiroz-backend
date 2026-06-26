package br.com.ifba.produto.entity;

import br.com.ifba.categoria.entity.Categoria;
import br.com.ifba.infraestructure.entity.PersistenceEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@ToString
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Produto extends PersistenceEntity {

    private String nome;
    private String descricao;
    private BigDecimal preco;
    private String imagemUrl;
    private boolean ativo;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

}
