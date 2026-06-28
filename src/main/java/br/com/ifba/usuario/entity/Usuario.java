package br.com.ifba.usuario.entity;

import br.com.ifba.infraestructure.entity.PersistenceEntity;
import br.com.ifba.perfilusuario.entity.PerfilUsuario;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    Boolean ativo;

    @ManyToOne
    @JoinColumn(name = "perfil_usuario_id")
    private PerfilUsuario perfilUsuario;

}
