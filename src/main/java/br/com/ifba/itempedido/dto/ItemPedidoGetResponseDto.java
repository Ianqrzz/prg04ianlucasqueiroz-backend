package br.com.ifba.itempedido.dto;

import br.com.ifba.produto.dto.ProdutoGetResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoGetResponseDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("quantidade")
    private Integer quantidade;

    @JsonProperty("valorUnitario")
    private BigDecimal valorUnitario;

    @JsonProperty("produto")
    private ProdutoGetResponseDto produto;
}