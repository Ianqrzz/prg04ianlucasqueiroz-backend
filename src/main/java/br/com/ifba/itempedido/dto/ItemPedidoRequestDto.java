package br.com.ifba.itempedido.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItemPedidoRequestDto {

    @NotNull
    private Long produtoId;

    @NotNull
    private Integer quantidade;
}
