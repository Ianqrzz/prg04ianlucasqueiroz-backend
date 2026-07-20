package br.com.ifba.pedido.dto;


import br.com.ifba.itempedido.dto.ItemPedidoRequestDto;
import br.com.ifba.itempedido.entity.ItemPedido;
import br.com.ifba.pedido.entity.StatusPedido;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoPostRequestDto {

    @JsonProperty(value = "usuario_id")
    @NotNull
    private Long usuario_id;

    @JsonProperty(value = "itensPedidos")
    @NotNull
    @Valid
    private List<ItemPedidoRequestDto> itensPedidos;

    @JsonProperty(value = "endereco_id")
    @NotNull(message = "O endereco de entrega é obrigatorio")
    private Long idEndereco;



}
