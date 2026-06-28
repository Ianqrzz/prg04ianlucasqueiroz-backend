package br.com.ifba.perfilusuario.entity;

import br.com.ifba.infraestructure.entity.PersistenceEntity;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PerfilUsuario extends PersistenceEntity {

    private String nome;
    private String descricao;

    @ElementCollection
    @CollectionTable(name = "perfil_permissoes")
    @Column(name = "permissao")
    private List<String> permissoes;
}