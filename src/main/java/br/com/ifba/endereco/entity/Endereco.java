package br.com.ifba.endereco.entity;

import br.com.ifba.infraestructure.entity.PersistenceEntity;
import br.com.ifba.usuario.entity.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Endereco extends PersistenceEntity {

    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

    // Relacionamento: Vários endereços podem pertencer a um único Usuário
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}