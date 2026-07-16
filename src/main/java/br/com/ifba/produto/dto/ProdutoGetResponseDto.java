package br.com.ifba.produto.dto;

import br.com.ifba.categoria.dto.CategoriaGetResponseDto;
import br.com.ifba.estoque.dto.EstoqueGetResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoGetResponseDto {


    @JsonProperty
    private Long id;

    @JsonProperty
    private String nome;

    @JsonProperty(value = "descricao")
    private String descricao;

    @JsonProperty(value = "preco")
    private BigDecimal preco;

    @JsonProperty(value = "imagem_url")
    private String imagemUrl;

    @JsonProperty(value = "ativo")
    private boolean ativo;


    @JsonProperty(value = "categoria")
    private CategoriaGetResponseDto categoria;

    @JsonProperty(value = "estoque")
    private EstoqueGetResponseDto estoque;
}
