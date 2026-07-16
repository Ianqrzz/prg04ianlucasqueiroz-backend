package br.com.ifba.estoque.entity;

import br.com.ifba.infraestructure.entity.PersistenceEntity;
import br.com.ifba.produto.entity.Produto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Estoque extends PersistenceEntity {

    @OneToOne
    @JoinColumn(name = "produto_id")
    @JsonBackReference
    private Produto produto;

    private Integer quantidade;

    private Integer quantidadeMinima;

    private LocalDateTime dataUltimaAtualizacao;

}
