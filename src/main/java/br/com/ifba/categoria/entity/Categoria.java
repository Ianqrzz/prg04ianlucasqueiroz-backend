package br.com.ifba.categoria.entity;


import br.com.ifba.infraestructure.entity.PersistenceEntity;
import jakarta.persistence.Entity;
import lombok.*;


@ToString
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Categoria extends PersistenceEntity {

    private String nome;
    private String descricao;


}
