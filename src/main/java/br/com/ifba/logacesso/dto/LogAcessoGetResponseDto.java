package br.com.ifba.logacesso.dto;

import br.com.ifba.usuario.dto.UsuarioGetResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogAcessoGetResponseDto {

    @JsonProperty
    private Long id;

    @JsonProperty(value = "acao")
    private String acao;

    @JsonProperty(value = "ip_origem")
    private String ipOrigem;

    @JsonProperty(value = "data_hora")
    private LocalDateTime dataHora;

    @JsonProperty(value = "usuario")
    private UsuarioGetResponseDto usuario;
}