package br.com.ifba.pedido.dto;


import br.com.ifba.endereco.dto.EnderecoGetResponseDto;
import br.com.ifba.itempedido.dto.ItemPedidoGetResponseDto;
import br.com.ifba.itempedido.dto.ItemPedidoRequestDto;
import br.com.ifba.usuario.dto.UsuarioGetResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PedidoGetResponseDto {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "Usuario")
    private UsuarioGetResponseDto usuario;

    @JsonProperty(value = "itensPedido")
    private List<ItemPedidoGetResponseDto> itensPedido;

    @JsonProperty(value = "numeroPedido")
    private String numeroPedido;

    @JsonProperty(value = "valorTotal")
    private BigDecimal valorTotal;

    @JsonProperty(value = "status")
    private String status;

    @JsonProperty(value = "endereco")
    private EnderecoGetResponseDto endereco;

    @JsonProperty(value = "dataHoraCriacao")
    private LocalDateTime dataHoraCriacao;

}
