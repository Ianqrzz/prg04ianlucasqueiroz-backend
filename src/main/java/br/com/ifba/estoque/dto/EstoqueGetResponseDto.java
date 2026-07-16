package br.com.ifba.estoque.dto;

import br.com.ifba.produto.dto.ProdutoGetResponseDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstoqueGetResponseDto {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "quantidade")
    private Integer quantidade;

    @JsonProperty(value = "quantidade_minima")
    private Integer quantidadeMinima;

    @JsonProperty(value = "atualizado_em")
    private LocalDateTime atualizadoEm;

    @JsonProperty(value = "produto")
    @JsonIgnore
    private ProdutoGetResponseDto produto;

}
