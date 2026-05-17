package br.com.ifba.usuario.entity;

import br.com.ifba.infraestructure.entity.PersistenceEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class Usuario extends PersistenceEntity {

    String nome;

    String email;

    String senha;

    String endereco;
}
