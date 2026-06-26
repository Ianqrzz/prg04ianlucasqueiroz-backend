package br.com.ifba.produto.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoPostRequestDto {

    @JsonProperty(value = "nome")
    @NotNull(message = "O nome é obrigatório")
    @NotBlank(message = "O nome não pode ser vazio")
    private String nome;

    @JsonProperty(value = "descricao")
    private String descricao;

    @JsonProperty(value = "preco")
    @NotNull(message = "O preço é obrigatório")
    @Positive(message = "O preço deve ser maior que zero")
    private BigDecimal preco;

    @JsonProperty(value = "imagem_url")
    private String imagemUrl;

    @JsonProperty(value = "ativo")
    private boolean ativo;

    @JsonProperty(value = "categoria_id")
    @NotNull(message = "A categoria é obrigatória")
    private Long categoriaId;
}