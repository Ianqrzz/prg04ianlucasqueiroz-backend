package br.com.ifba.estoque.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstoquePostRequestDto {


    @NotNull
    @JsonProperty(value = "idProduto")
    private Long idProduto;

    @NotNull
    @JsonProperty(value = "quantidade")
    private Integer quantidade;

    @NotNull
    @JsonProperty(value = "quantidadeMinima")
    private Integer quantidadeMinima;



}
