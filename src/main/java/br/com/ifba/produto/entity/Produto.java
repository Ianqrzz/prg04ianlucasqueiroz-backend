package br.com.ifba.produto.entity;

import br.com.ifba.infraestructure.entity.PersistenceEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Persistence;
import lombok.*;


@ToString
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Produto extends PersistenceEntity {

    private String nome;
    private String descricao;
    private Double preco;
}
